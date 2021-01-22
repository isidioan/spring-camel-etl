package com.iioannou.cameletlprocessor.repository;

import com.iioannou.cameletlprocessor.domain.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
