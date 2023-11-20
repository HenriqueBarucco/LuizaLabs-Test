package com.henriquebarucco.luizalabs.configuration.usecases;

import com.henriquebarucco.luizalabs.core.gateways.FileGateway;
import com.henriquebarucco.luizalabs.core.usecases.FileReaderInteractor;
import com.henriquebarucco.luizalabs.core.usecases.impl.FileReaderInteractorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileReaderInteractorConfig {

    @Bean
    public FileReaderInteractor fileReaderInteractor(FileGateway fileGateway) {
        return new FileReaderInteractorImpl(fileGateway);
    }
}
