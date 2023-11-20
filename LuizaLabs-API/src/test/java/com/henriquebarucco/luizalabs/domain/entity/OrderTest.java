package com.henriquebarucco.luizalabs.domain.entity;

import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.core.entity.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testCalculateTotal() {
        Product product1 = new Product(1L, 10.0);
        Product product2 = new Product(2L, 40.0);

        Order order = new Order(1L, LocalDate.now(), List.of(product1, product2));

        assertEquals(50.0, order.getTotal());
    }
}