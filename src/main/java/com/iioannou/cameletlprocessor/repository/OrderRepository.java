package com.iioannou.cameletlprocessor.repository;


import com.iioannou.cameletlprocessor.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
