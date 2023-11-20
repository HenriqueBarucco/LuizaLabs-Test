package com.henriquebarucco.luizalabs.configuration.gateways;

import com.henriquebarucco.luizalabs.core.gateways.FileGateway;
import com.henriquebarucco.luizalabs.core.usecases.UserInteractor;
import com.henriquebarucco.luizalabs.dataprovider.gateways.file.FileReaderGateway;
import com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory.FileObjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileGatewayConfig {

    @Bean
    public FileGateway fileGateway(UserInteractor userInteractor, FileObjectFactory fileObjectFactory) {
        return new FileReaderGateway(userInteractor, fileObjectFactory);
    }
}
