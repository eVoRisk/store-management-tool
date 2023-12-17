package com.store.management.tool;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@OpenAPIDefinition
public class StoreManagementToolApplication {

    public static void main(final String[] args) {
        SpringApplication.run(StoreManagementToolApplication.class, args);
    }
}
