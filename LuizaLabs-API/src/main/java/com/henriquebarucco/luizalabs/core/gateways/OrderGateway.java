package com.henriquebarucco.luizalabs.core.gateways;

import com.henriquebarucco.luizalabs.core.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderGateway {

    List<Order> listAllOrders();

    List<Order> listAllOrdersByDate(LocalDate startDate, LocalDate endDate);

    Order getOrderById(Long orderId);
}
