package com.henriquebarucco.luizalabs.entrypoints.order;

import com.henriquebarucco.luizalabs.core.usecases.OrderInteractor;
import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.entrypoints.UserDTOMapper;
import com.henriquebarucco.luizalabs.entrypoints.user.dto.OrderResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/orders")
public class OrderController {

    private final OrderInteractor orderInteractor;
    private final UserDTOMapper userDTOMapper;

    public OrderController(OrderInteractor orderInteractor, UserDTOMapper userDTOMapper) {
        this.orderInteractor = orderInteractor;
        this.userDTOMapper = userDTOMapper;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> listAllOrders(
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        List<Order> orders;

        if (startDate != null && endDate != null) {
            orders = orderInteractor.listAllOrdersByDate(startDate, endDate);
        } else {
            orders = orderInteractor.listAllOrders();
        }

        List<OrderResponse> userResponses = orders.stream().map(userDTOMapper::toOrderResponse).toList();

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        Order order = orderInteractor.getOrderById(id);
        OrderResponse orderResponse = userDTOMapper.toOrderResponse(order);

        return ResponseEntity.ok(orderResponse);
    }
}
