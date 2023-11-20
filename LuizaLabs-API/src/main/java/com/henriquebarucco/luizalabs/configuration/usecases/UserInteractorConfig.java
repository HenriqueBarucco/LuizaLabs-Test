package com.henriquebarucco.luizalabs.configuration.usecases;

import com.henriquebarucco.luizalabs.core.gateways.UserGateway;
import com.henriquebarucco.luizalabs.core.usecases.UserInteractor;
import com.henriquebarucco.luizalabs.core.usecases.impl.UserInteractorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInteractorConfig {

    @Bean
    public UserInteractor userInteractor(UserGateway userGateway) {
        return new UserInteractorImpl(userGateway);
    }
}
