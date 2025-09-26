package com.example.product.application.controller;

import com.example.proudct.domain.usecase.create.CreateProductUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

class ProductControllerWebTestCase {
    CreateProductUseCase createProductUseCase = p -> Mono.just(p.getId());

    private final WebTestClient webTestClient = WebTestClient.bindToController(new ProductController(createProductUseCase)).build();

    @Test
    void createProductShouldWork() {
        webTestClient.post()
                .uri("/products/create")
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errors").isEqualTo(null)
                .jsonPath("$.data").exists();
    }
}