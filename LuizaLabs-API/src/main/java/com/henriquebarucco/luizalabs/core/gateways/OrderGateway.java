package com.henriquebarucco.luizalabs.core.gateways;

import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.core.entity.Product;
import com.henriquebarucco.luizalabs.core.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface OrderGateway {

    List<Order> listAllOrders();

    List<Order> listAllOrdersByDate(LocalDate startDate, LocalDate endDate);

    Order getOrderById(Long orderId);

    Order createOrder(User user, Order order);

    void addProductToOrder(Order order, Product product);
}
