package com.iioannou.cameletlprocessor.process.shipment;

import com.iioannou.cameletlprocessor.domain.Shipment;
import com.iioannou.cameletlprocessor.util.Keys;
import com.iioannou.cameletlprocessor.util.ParseUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ShipmentMapper {

    public List<Shipment> createShipments(List<Map<String, Object>> shipmentMaps) {
        List<Shipment> ordersList = new ArrayList<>();

        for (Map<String, Object> shMap : shipmentMaps) {
            Shipment shipment = new Shipment();
            shipment.setId(Long.parseLong(shMap.get(Keys.ID).toString()));
            shipment.setQuantity(Long.parseLong(shMap.get(Keys.QUANTITY).toString()));
            shipment.setOrderId(Long.parseLong(shMap.get(Keys.ORDER_ID).toString()));
            shipment.setStatus(Long.parseLong(shMap.get(Keys.STATUS).toString()));
            shipment.setCreatedDateTime(ParseUtility.parseDateValue(shMap.get(Keys.CREATED_DATETIME).toString()).orElse(new Date()));
            shipment.setUpdatedDateTime(StringUtils.isNotEmpty(shMap.get(Keys.LAST_UPDATE_DATETIME).toString()) ?
                    (ParseUtility.parseDateValue(shMap.get(Keys.LAST_UPDATE_DATETIME).toString()).orElse(new Date())) : null);
            shipment.setErrorCode(Long.parseLong(shMap.get(Keys.ERROR_CODE).toString()));
            shipment.setErrorMsg(shMap.get(Keys.ERROR_MSG).toString());

            ordersList.add(shipment);

        }
        return ordersList;
    }
}
