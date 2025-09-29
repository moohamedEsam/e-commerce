package com.example.product.application;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableR2dbcRepositories
@EnableRabbit
@ComponentScan(basePackages = {"com.example.product.application.*"})
public class ProductApplication {

    static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
