package com.henriquebarucco.luizalabs.configuration.mapper;

import com.henriquebarucco.luizalabs.entrypoints.UserDTOMapper;
import com.henriquebarucco.luizalabs.entrypoints.file.mapper.FilesDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTOMapperConfig {

    @Bean
    public UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }

    @Bean
    public FilesDTOMapper filesDTOMapper() {
        return new FilesDTOMapper();
    }
}
