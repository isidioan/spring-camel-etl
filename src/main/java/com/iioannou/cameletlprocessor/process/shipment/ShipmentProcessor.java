package com.iioannou.cameletlprocessor.process.shipment;

import com.iioannou.cameletlprocessor.service.FetchKeyService;
import com.iioannou.cameletlprocessor.service.validator.ShipmentValidator;
import com.iioannou.cameletlprocessor.util.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ShipmentProcessor implements Processor {

    private final FetchKeyService fetchKeyService;

    public ShipmentProcessor(FetchKeyService fetchKeyService) {
        this.fetchKeyService = fetchKeyService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<Map<String, Object>> data = (List<Map<String, Object>>) exchange.getIn().getBody();
        log.info("list size {}", data.size());

        List<Long> orderIds = fetchKeyService.fetchAllOrderIds();

        ShipmentValidator validator = new ShipmentValidator(orderIds);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Map<String, Object> da : data) {
            list.add(validator.validateRecord(da, data.indexOf(da) + 1));
        }

        if (list.stream().allMatch(map -> map.containsKey(Keys.ERROR_CODE) && map.get(Keys.ERROR_CODE).equals("0"))) {
            exchange.getIn().setBody(list);
        } else {
            exchange.getIn().setHeader("Error", "true");
            Map<String, Object> headers = data.get(0);
            Map<String, Object> writtableHeaders = new LinkedHashMap<>();
            headers.keySet().forEach(h -> writtableHeaders.put(h, h));
            list.add(0, writtableHeaders);
            exchange.getIn().setBody(list);
            String fileName = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);
            String[] text = fileName.split("\\.");
            StringBuilder sb = new StringBuilder();
            sb.append(text[0]);
            sb.append("_reply.");
            for (int i = 1; i < text.length; i++) {
                sb.append(text[i].concat("."));
            }
            exchange.getIn().setHeader(Exchange.FILE_NAME, sb.toString());
        }
    }

}
