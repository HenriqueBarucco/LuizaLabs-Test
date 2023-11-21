package com.henriquebarucco.luizalabs.entrypoints.user.dto;

import java.util.List;

public record OrderResponse(
    Long order_id,
    String total,
    String date,
    List<ProductResponse> products
) {
}
