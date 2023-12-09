package com.store.management.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class StoreManagementToolApplication {

    public static void main(final String[] args) {
        SpringApplication.run(StoreManagementToolApplication.class, args);
    }
}
