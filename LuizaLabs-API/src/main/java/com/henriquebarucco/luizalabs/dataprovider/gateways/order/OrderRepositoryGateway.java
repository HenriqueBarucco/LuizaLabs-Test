package com.henriquebarucco.luizalabs.dataprovider.gateways.order;

import com.henriquebarucco.luizalabs.core.entity.Product;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.core.exceptions.ResourceNotFoundException;
import com.henriquebarucco.luizalabs.core.gateways.OrderGateway;
import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.dataprovider.gateways.order.mapper.OrderEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderEntity;
import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderRepository;
import com.henriquebarucco.luizalabs.dataprovider.persistence.product.ProductEntity;
import com.henriquebarucco.luizalabs.dataprovider.persistence.product.ProductRepository;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserEntity;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDate;
import java.util.List;

public class OrderRepositoryGateway implements OrderGateway {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderEntityMapper orderEntityMapper;

    public OrderRepositoryGateway(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderEntityMapper orderEntityMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
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

    @Cacheable(value = "orders", key = "#startDate.toString().concat('-').concat(#endDate.toString())")
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
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return orderEntityMapper.toDomain(orderEntity);
    }

    @CacheEvict(value = {"orders", "users"}, allEntries = true)
    public Order createOrder(User user, Order order) {
        UserEntity userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        OrderEntity orderEntity = orderRepository.findById(order.getId()).orElse(new OrderEntity(order.getId(), order.getDate(), userEntity));
        orderRepository.save(orderEntity);

        return orderEntityMapper.toDomain(orderEntity);
    }

    @CacheEvict(value = {"orders", "users"}, allEntries = true)
    public void addProductToOrder(Order order, Product product) {
        OrderEntity orderEntity = orderRepository.findById(order.getId()).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        ProductEntity productEntity = productRepository.save(new ProductEntity(product.getId(), product.getValue(), orderEntity));

        orderEntity.addProduct(productEntity);
    }
}
