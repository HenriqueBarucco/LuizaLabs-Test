package com.henriquebarucco.luizalabs.core.usecases.impl;

import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.core.entity.Product;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.core.gateways.OrderGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OrderInteractorImplTest {

    @InjectMocks
    private OrderInteractorImpl orderInteractorImpl;

    @Mock
    private OrderGateway orderGateway;

    @Mock
    private Order order;

    @Mock
    private User user;

    @Mock
    private Product product;

    @Test
    public void testShouldCreateOrder() {
        orderInteractorImpl.createOrder(user, order);

        then(orderGateway).should().createOrder(user, order);
    }

    @Test
    public void testShouldAddProductToOrder() {
        orderInteractorImpl.addProductToOrder(order, product);

        then(orderGateway).should().addProductToOrder(order, product);
    }

    @Test
    public void testShouldGetOrderById() {
        orderInteractorImpl.getOrderById(1L);

        then(orderGateway).should().getOrderById(1L);
    }

    @Test
    public void testShouldListAllOrdersByDate() {
        orderInteractorImpl.listAllOrdersByDate(LocalDate.now(), LocalDate.now());

        then(orderGateway).should().listAllOrdersByDate(LocalDate.now(), LocalDate.now());
    }

    @Test
    public void testShouldListAllOrders() {
        orderInteractorImpl.listAllOrders();

        then(orderGateway).should().listAllOrders();
    }
}