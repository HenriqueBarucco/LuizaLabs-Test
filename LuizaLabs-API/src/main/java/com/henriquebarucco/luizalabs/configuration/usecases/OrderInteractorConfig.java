package com.henriquebarucco.luizalabs.configuration.usecases;

import com.henriquebarucco.luizalabs.core.gateways.OrderGateway;
import com.henriquebarucco.luizalabs.core.usecases.OrderInteractor;
import com.henriquebarucco.luizalabs.core.usecases.impl.OrderInteractorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderInteractorConfig {

    @Bean
    public OrderInteractor orderInteractor(OrderGateway orderGateway) {
        return new OrderInteractorImpl(orderGateway);
    }
}
