package com.iioannou.cameletlprocessor.service;

import com.iioannou.cameletlprocessor.domain.Orders;
import com.iioannou.cameletlprocessor.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrdersService {

    private final OrderRepository repository;

    public OrdersService(OrderRepository repository) {
        this.repository = repository;
    }


    public void persistOrder(Orders order) {
        log.info("Order object {}", order.toString());
        repository.save(order);
    }

    public void persistOrders(List<Orders> orders) {
        orders.forEach(o -> {
            log.info("Order object {}", o.toString());
            repository.save(o);
        });
    }
}
