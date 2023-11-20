package com.henriquebarucco.luizalabs.configuration.gateways;

import com.henriquebarucco.luizalabs.core.gateways.UserGateway;
import com.henriquebarucco.luizalabs.dataprovider.gateways.user.UserRepositoryGateway;
import com.henriquebarucco.luizalabs.dataprovider.gateways.user.mapper.UserEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderRepository;
import com.henriquebarucco.luizalabs.dataprovider.persistence.product.ProductRepository;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserGatewayConfig {

    @Bean
    public UserGateway userGateway(UserRepository userRepository, OrderRepository orderRepository, ProductRepository productRepository, UserEntityMapper userEntityMapper) {
        return new UserRepositoryGateway(userRepository, orderRepository, productRepository, userEntityMapper);
    }
}
