package com.henriquebarucco.luizalabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LuizaLabsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuizaLabsApplication.class, args);
    }

}
