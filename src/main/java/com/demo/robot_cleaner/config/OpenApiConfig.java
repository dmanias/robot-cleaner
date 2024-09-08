package com.demo.robot_cleaner.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI robotCleanerOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Robot Cleaner API")
                        .description("API for controlling a robot cleaner")
                        .version("v1.0"));
    }
}