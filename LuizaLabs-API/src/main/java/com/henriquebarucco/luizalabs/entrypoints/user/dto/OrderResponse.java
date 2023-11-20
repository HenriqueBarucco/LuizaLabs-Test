package com.henriquebarucco.luizalabs.entrypoints.user.dto;

import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
    Long order_id,
    String total,
    LocalDate date,
    List<ProductResponse> products
) {
}
