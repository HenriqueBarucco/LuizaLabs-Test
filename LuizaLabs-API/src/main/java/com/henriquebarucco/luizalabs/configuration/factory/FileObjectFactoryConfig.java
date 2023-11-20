package com.henriquebarucco.luizalabs.configuration.factory;

import com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.FileObjectFactory;
import com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.impl.FileObjectFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileObjectFactoryConfig {

    @Bean
    public FileObjectFactory fileObjectFactory() {
        return new FileObjectFactoryImpl();
    }
}
