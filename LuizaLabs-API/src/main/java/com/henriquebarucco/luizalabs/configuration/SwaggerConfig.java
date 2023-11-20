package com.henriquebarucco.luizalabs.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.UnknownHostException;
import java.util.List;

@EnableWebMvc
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() throws UnknownHostException {

        Server server = new Server();
        server.setUrl("/");
        server.setDescription("Local");

        Contact contact = new Contact();
        contact.setEmail("henrique_barucco@hotmail.com");
        contact.setName("Henrique Barucco");
        contact.setUrl("https://www.henriquebarucco.com.br");

        Info info = new Info()
                .title("LuizaLabs - Test")
                .version("1.0.0")
                .description("API para o teste técnico de backend.")
                .contact(contact)
                .termsOfService("https://henriquebarucco.com.br/");

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
