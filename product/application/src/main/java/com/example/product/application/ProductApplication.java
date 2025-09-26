package com.example.product.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableR2dbcRepositories
@ComponentScan(basePackages = {"com.example.product.application.configuration"})
public class ProductApplication {

    static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
