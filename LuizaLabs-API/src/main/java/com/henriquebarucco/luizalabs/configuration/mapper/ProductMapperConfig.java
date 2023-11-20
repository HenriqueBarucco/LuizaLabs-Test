package com.henriquebarucco.luizalabs.configuration.mapper;

import com.henriquebarucco.luizalabs.dataprovider.gateways.user.mapper.ProductEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductMapperConfig {

    @Bean
    public ProductEntityMapper productEntityMapper() {
        return new ProductEntityMapper();
    }
}
