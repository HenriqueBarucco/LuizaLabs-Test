package com.henriquebarucco.luizalabs.entrypoints;

import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.core.entity.Product;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.entrypoints.user.dto.OrderResponse;
import com.henriquebarucco.luizalabs.entrypoints.user.dto.ProductResponse;
import com.henriquebarucco.luizalabs.entrypoints.user.dto.UserResponse;

import java.util.stream.Collectors;

public class UserDTOMapper {

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getOrders().stream().map(this::toOrderResponse).collect(Collectors.toList())
        );
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getTotal().toString(),
                order.getDate(),
                order.getProducts().stream().map(this::toProductResponse).collect(Collectors.toList())
        );
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getValue().toString()
        );
    }
}
