package com.henriquebarucco.luizalabs.configuration.mapper;

import com.henriquebarucco.luizalabs.dataprovider.gateways.order.mapper.OrderEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.gateways.user.mapper.ProductEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMapperConfig {

    @Bean
    public OrderEntityMapper orderEntityMapper(ProductEntityMapper productEntityMapper) {
        return new OrderEntityMapper(productEntityMapper);
    }
}
