package com.henriquebarucco.luizalabs.entrypoints.order;

import com.henriquebarucco.luizalabs.core.usecases.OrderInteractor;
import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.entrypoints.UserDTOMapper;
import com.henriquebarucco.luizalabs.entrypoints.user.dto.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Order Controller", description = "Operações relacionadas com os pedidos.")
@RestController
@RequestMapping("v1/orders")
public class OrderController {

    private final OrderInteractor orderInteractor;
    private final UserDTOMapper userDTOMapper;

    public OrderController(OrderInteractor orderInteractor, UserDTOMapper userDTOMapper) {
        this.orderInteractor = orderInteractor;
        this.userDTOMapper = userDTOMapper;
    }

    @Operation(summary = "Get all orders.", description = "Retorna todos os pedidos salvos no banco de dados. " +
            "Caso seja passado uma data de início e fim, retorna todos os pedidos entre essas datas.")
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

    @Operation(summary = "Get order by id.", description = "Retorna um pedido pelo id.")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        Order order = orderInteractor.getOrderById(id);
        OrderResponse orderResponse = userDTOMapper.toOrderResponse(order);

        return ResponseEntity.ok(orderResponse);
    }
}
