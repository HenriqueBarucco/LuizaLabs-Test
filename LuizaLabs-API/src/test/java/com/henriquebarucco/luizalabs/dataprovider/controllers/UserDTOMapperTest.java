package com.henriquebarucco.luizalabs.dataprovider.controllers;

import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.core.entity.Product;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.entrypoints.UserDTOMapper;
import com.henriquebarucco.luizalabs.entrypoints.user.dto.OrderResponse;
import com.henriquebarucco.luizalabs.entrypoints.user.dto.ProductResponse;
import com.henriquebarucco.luizalabs.entrypoints.user.dto.UserResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOMapperTest {

    private final UserDTOMapper userDTOMapper;

    public UserDTOMapperTest() {
        this.userDTOMapper = new UserDTOMapper();
    }

    @Test
    public void testToUserResponse() {
        Product product = new Product(1L, 10.0);
        Order order = new Order(1L, LocalDate.of(2023, 11, 20), List.of(product));
        User user = new User(1L, "Henrique", List.of(order));

        UserResponse userResponse = userDTOMapper.toUserResponse(user);

        UserResponse expectedUserResponse = new UserResponse(1L, "Henrique", List.of(new OrderResponse(1L, "10.00", "2023-11-20", List.of(new ProductResponse(1L, "10.0")))));

        assertEquals(expectedUserResponse, userResponse);
    }

}