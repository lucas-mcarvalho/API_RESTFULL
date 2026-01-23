package com.udemyCourse.course.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI CustomOpenAPI(){
            return new OpenAPI().info(new Info().title("Rest API'S Restfull from Java and Spring Boot,Kubernets and Docker.")
                    .version("v1")
                    .description("Rest API'S Restfull from Java and Spring Boot,Kubernets and Docker.").
                    termsOfService("https://github.com/lucas-mcarvalho").license(new License()
                            .name("apache 2.0").url("https://github.com/lucas-mcarvalho")));
    }
}
