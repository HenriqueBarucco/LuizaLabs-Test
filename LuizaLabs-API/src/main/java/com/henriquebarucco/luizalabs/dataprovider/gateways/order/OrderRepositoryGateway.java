package com.henriquebarucco.luizalabs.dataprovider.gateways.order;

import com.henriquebarucco.luizalabs.core.gateways.OrderGateway;
import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.dataprovider.gateways.order.mapper.OrderEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderEntity;
import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderRepository;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDate;
import java.util.List;

public class OrderRepositoryGateway implements OrderGateway {

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    public OrderRepositoryGateway(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper) {
        this.orderRepository = orderRepository;
        this.orderEntityMapper = orderEntityMapper;
    }

    @Cacheable("orders")
    @Override
    public List<Order> listAllOrders() {
        List<OrderEntity> orderEntities = (List<OrderEntity>) orderRepository.findAll();

        return orderEntities.stream()
                .map(orderEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Order> listAllOrdersByDate(LocalDate startDate, LocalDate endDate) {
        List<OrderEntity> orderEntities = orderRepository.findAllInRange(startDate, endDate);

        return orderEntities.stream()
                .map(orderEntityMapper::toDomain)
                .toList();
    }

    @Cacheable(value = "orders", key = "#orderId")
    @Override
    public Order getOrderById(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found")); // TODO - Create a custom exception

        return orderEntityMapper.toDomain(orderEntity);
    }
}
