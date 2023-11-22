package com.henriquebarucco.luizalabs.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.UnknownHostException;
import java.util.List;

@EnableWebMvc
@Configuration
public class SwaggerConfig {

    @Value("${application.name}")
    private String name;
    @Value("${application.version}")
    private String version;
    @Value("${application.description}")
    private String description;

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
                .title(name)
                .version(version)
                .description(description)
                .contact(contact)
                .termsOfService("https://henriquebarucco.com.br/");

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
