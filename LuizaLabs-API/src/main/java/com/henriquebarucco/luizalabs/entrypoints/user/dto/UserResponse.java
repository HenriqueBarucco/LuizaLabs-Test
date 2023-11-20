package com.henriquebarucco.luizalabs.entrypoints.user.dto;

import java.util.List;

public record UserResponse(
    Long user_id,
    String name,
    List<OrderResponse> orders
) {
}
