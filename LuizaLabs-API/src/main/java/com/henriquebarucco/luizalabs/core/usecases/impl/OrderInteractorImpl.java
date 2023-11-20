package com.henriquebarucco.luizalabs.core.usecases.impl;

import com.henriquebarucco.luizalabs.core.gateways.OrderGateway;
import com.henriquebarucco.luizalabs.core.usecases.OrderInteractor;
import com.henriquebarucco.luizalabs.core.entity.Order;

import java.time.LocalDate;
import java.util.List;

public class OrderInteractorImpl implements OrderInteractor {

    private final OrderGateway orderGateway;

    public OrderInteractorImpl(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @Override
    public List<Order> listAllOrders() {
        return orderGateway.listAllOrders();
    }

    @Override
    public List<Order> listAllOrdersByDate(LocalDate startDate, LocalDate endDate) {
        return orderGateway.listAllOrdersByDate(startDate, endDate);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderGateway.getOrderById(orderId);
    }
}
