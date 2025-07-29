package com.wallacewebs.springai.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movies AI API")
                        .version("1.0.0")
                        .description("Microserviço que utiliza IA para buscar filmes baseado em uma lista de atores")
                        .contact(new Contact()
                                .name("Wallace Webs")
                                .url("https://wallacewebs.com")
                                .email("contato@wallacewebs.com")));
    }
}
