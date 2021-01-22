package com.iioannou.cameletlprocessor.service;

import com.iioannou.cameletlprocessor.domain.Shipment;
import com.iioannou.cameletlprocessor.repository.ShipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public void persistShipment(Shipment shipment) {
        shipmentRepository.save(shipment);
    }

    public void persistShipments(List<Shipment> shipments) {
        shipments.forEach(o -> {
            log.info("Order object {}", o.toString());
            shipmentRepository.save(o);
        });
    }

}
