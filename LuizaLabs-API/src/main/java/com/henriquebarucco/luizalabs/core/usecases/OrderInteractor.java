package com.henriquebarucco.luizalabs.core.usecases;


import com.henriquebarucco.luizalabs.core.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderInteractor {

    List<Order> listAllOrders();

    List<Order> listAllOrdersByDate(LocalDate startDate, LocalDate endDate);

    Order getOrderById(Long orderId);
}
