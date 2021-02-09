package com.iioannou.cameletlprocessor.routes;

import com.iioannou.cameletlprocessor.domain.Orders;
import com.iioannou.cameletlprocessor.domain.Shipment;
import com.iioannou.cameletlprocessor.domain.Transactions;
import com.iioannou.cameletlprocessor.process.shipment.ShipmentMapper;
import com.iioannou.cameletlprocessor.process.shipment.ShipmentProcessor;
import com.iioannou.cameletlprocessor.service.OrdersService;
import com.iioannou.cameletlprocessor.service.ShipmentService;
import com.iioannou.cameletlprocessor.service.TransactionService;
import com.iioannou.cameletlprocessor.util.FileSorter;
import com.iioannou.cameletlprocessor.util.ListAggrStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.azure.storage.blob.BlobConstants;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class FileRoute extends RouteBuilder {

    private static final String AMPERSAND = "&";
    private final BindyCsvDataFormat orderCsvDataFormat = new BindyCsvDataFormat(Orders.class);
    private final BindyCsvDataFormat transactionCsvDataFormat = new BindyCsvDataFormat(Transactions.class);
    private final BindyCsvDataFormat shipmentCsvDataFormat = new BindyCsvDataFormat(Shipment.class);

    private final OrdersService ordersService;
    private final TransactionService transactionService;
    private final ShipmentService shipmentService;
    private final ShipmentMapper shipmentMapper;
    private final ShipmentProcessor shipmentProcessor;

    public FileRoute(OrdersService ordersService, TransactionService transactionService, ShipmentService shipmentService, ShipmentMapper shipmentMapper, ShipmentProcessor shipmentProcessor) {
        this.ordersService = ordersService;
        this.transactionService = transactionService;
        this.shipmentService = shipmentService;
        this.shipmentMapper = shipmentMapper;
        this.shipmentProcessor = shipmentProcessor;
    }

    @Value("${source.location}")
    private String sourceLocation;

    @Value("${target.location}")
    private String targetLocation;

    @Value("${target.error.location}")
    private String targetErrorLocation;

    @Value("${cron.start}")
    private String cronStart;

    @Value("${cron.stop}")
    private String cronStop;

    @Value("${route.autostart}")
    private String routeAutostart;

    @Value("${azure.containerName}")
    private String azureContainerName;


    @Override
    public void configure() throws Exception {

        CsvDataFormat csv = new CsvDataFormat();
        csv.setDelimiter(";");
        csv.setUseMaps("true");

        CronScheduledRoutePolicy startPolicy = new CronScheduledRoutePolicy();
        startPolicy.setRouteStartTime(cronStart);
        startPolicy.setRouteStopTime(cronStop);

        onException(Exception.class)
                .maximumRedeliveries(1)
                .handled(true)
                .log(LoggingLevel.INFO, "${file:name}")
                .log("Exception occurred due: ${exception}")
                .useOriginalMessage()
                .to("file://".concat(targetErrorLocation));


        getContext().getRegistry().bind("fileSorter", new FileSorter<Object>());


        from(buildPathUrl())
                .routeId("file-sync")
                .autoStartup(routeAutostart)
                .routePolicy(startPolicy)
                .log(LoggingLevel.INFO, "${file:name}")
                .process(exchange -> {
                    String newBody = exchange.getIn().getBody(String.class)
                            .replaceAll("\\uFEFF", "");
                    exchange.getMessage().setBody((newBody));
                })
                .choice()
                .when(header("CamelFileName").contains("orders"))
                .log(LoggingLevel.INFO, "Order file")
                .to("direct:orderRoute")
                .when(header("CamelFileName").contains("transactions"))
                .log(LoggingLevel.INFO, "Transaction file")
                .to("direct:transactionRoute")
                .otherwise()
                .log(LoggingLevel.INFO, "Shipment file")
                .to("direct:shipmentRoute");


        from("direct:orderRoute")
                .log(LoggingLevel.INFO, "${body}")
                .unmarshal(orderCsvDataFormat)
                .split(body(), new ListAggrStrategy())
                .streaming()
                .shareUnitOfWork()
                .bean(ordersService, "persistOrder")
                .marshal(orderCsvDataFormat)
                .log(LoggingLevel.INFO, "${body}")
                .log(LoggingLevel.INFO, "${file:name}")
                .to("file://".concat(targetLocation))
                .process(exchange -> {
                    String fileName = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);
                    Object body = exchange.getIn().getBody();
                    exchange.getIn().setHeader(BlobConstants.BLOB_NAME, fileName);
                    exchange.getIn().setBody(body);
                })
                .to(String.format("azure-storage-blob:/%s?blobName=blob&operation=uploadBlockBlob&serviceClient=#azureClient", azureContainerName));


        from("direct:transactionRoute")
                .unmarshal(transactionCsvDataFormat)
                .split(body(), new ListAggrStrategy())
                .streaming()
                .shareUnitOfWork()
                .bean(transactionService, "persistTransaction")
                .marshal(transactionCsvDataFormat)
                .log(LoggingLevel.INFO, "${body}")
                .log(LoggingLevel.INFO, "${file:name}")
                .to("file://".concat(targetLocation))
                .process(exchange -> {
                    String fileName = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);
                    Object body = exchange.getIn().getBody();
                    exchange.getIn().setHeader(BlobConstants.BLOB_NAME, fileName);
                    exchange.getIn().setBody(body);
                })
                .to(String.format("azure-storage-blob:/%s?blobName=blob&operation=uploadBlockBlob&serviceClient=#azureClient", azureContainerName));


        from("direct:shipmentRoute")
                .unmarshal(csv)
                .process(shipmentProcessor)
                .choice()
                .when(header("Error").isNotNull())
                .marshal(csv)
                .to("file://".concat(targetErrorLocation))
                .otherwise()
                .bean(shipmentMapper, "createShipments")
                .bean(shipmentService, "persistShipments")
                .marshal(shipmentCsvDataFormat)
                .log(LoggingLevel.INFO, "${body}")
                .log(LoggingLevel.INFO, "${file:name}")
                .to("file://".concat(targetLocation))
                .process(exchange -> {
            String fileName = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);
            Object body = exchange.getIn().getBody();
            exchange.getIn().setHeader(BlobConstants.BLOB_NAME, fileName);
            exchange.getIn().setBody(body);
        })
                .to(String.format("azure-storage-blob:/%s?blobName=blob&operation=uploadBlockBlob&serviceClient=#azureClient", azureContainerName));

    }


    private String buildPathUrl() {
        StringBuilder stringBuilder = new StringBuilder("file://");
        stringBuilder.append(sourceLocation)
                .append("?delete=true&sorter=#fileSorter");

        return stringBuilder.toString();

    }
}
