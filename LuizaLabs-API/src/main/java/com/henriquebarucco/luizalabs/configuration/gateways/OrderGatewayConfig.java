package com.henriquebarucco.luizalabs.configuration.gateways;

import com.henriquebarucco.luizalabs.core.gateways.OrderGateway;
import com.henriquebarucco.luizalabs.dataprovider.gateways.order.OrderRepositoryGateway;
import com.henriquebarucco.luizalabs.dataprovider.gateways.order.mapper.OrderEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderGatewayConfig {

    @Bean
    public OrderGateway orderGateway(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper) {
        return new OrderRepositoryGateway(orderRepository, orderEntityMapper);
    }
}
