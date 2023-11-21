package com.henriquebarucco.luizalabs.configuration.gateways;

import com.henriquebarucco.luizalabs.core.gateways.OrderGateway;
import com.henriquebarucco.luizalabs.dataprovider.gateways.order.OrderRepositoryGateway;
import com.henriquebarucco.luizalabs.dataprovider.gateways.order.mapper.OrderEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderRepository;
import com.henriquebarucco.luizalabs.dataprovider.persistence.product.ProductRepository;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderGatewayConfig {

    @Bean
    public OrderGateway orderGateway(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderEntityMapper orderEntityMapper) {
        return new OrderRepositoryGateway(orderRepository, userRepository, productRepository, orderEntityMapper);
    }
}
