package com.desafio.forohub.infra.springdocs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocsConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .components(new Components()
        .addSecuritySchemes("bearer-key",
        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))).info(new Info()
                        .title("Foro Hub API")
                        .description("API Rest del proyecto Foro Hub, una plataforma para gestionar temas de discusión. Permite la creación de tópicos, respuestas y gestión de usuarios. Incluye funcionalidades CRUD para las entidades tópicos y respuestas por el momento.")
                        .contact(new Contact()
                                .name("Erika Gimenez")
                                .url("https://www.linkedin.com/in/erika-gimenez/"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
