package com.henriquebarucco.luizalabs.configuration.mapper;

import com.henriquebarucco.luizalabs.dataprovider.gateways.order.mapper.OrderEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.gateways.user.mapper.UserEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapperConfig {

    @Bean
    public UserEntityMapper userEntityMapper(OrderEntityMapper orderEntityMapper) {
        return new UserEntityMapper(orderEntityMapper);
    }
}
