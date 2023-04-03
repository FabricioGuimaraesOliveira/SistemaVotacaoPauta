package com.sistema.votacao.infrastructure.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI springSwagger() {

        Contact contact = new Contact();
        contact.setEmail("fguimaraesoliveira@gmail.com");
        contact.setName("Developer");
        contact.setUrl("https://www.developer.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Sistema de Votação de Pauta")
                .version("1.0")
                .contact(contact)
                .description("Esta API disponibiliza endpoints para gerenciamento do sistema de votação").termsOfService("https://www.developer.com/terms")
                .license(mitLicense);
        return new OpenAPI()
                .info(info);

    }
}
