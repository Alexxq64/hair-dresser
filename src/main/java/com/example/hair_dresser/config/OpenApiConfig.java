package com.example.hair_dresser.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hair Dresser API")
                        .version("1.0")
                        .description("Документация REST API для управления парикмахерской: клиенты, процедуры, стилисты, посещения и отзывы."));
    }
}
