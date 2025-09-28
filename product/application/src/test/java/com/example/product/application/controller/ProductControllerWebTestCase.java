package com.example.product.application.controller;

import com.example.proudct.domain.model.Product;
import com.example.proudct.domain.usecase.create.CreateProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class ProductControllerWebTestCase {
    @Mock
    CreateProductUseCase createProductUseCase;

    @InjectMocks
    ProductController productController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        Mockito.when(createProductUseCase.execute(Mockito.any()))
                .thenReturn(Mono.just(Product.empty()));
        webTestClient = WebTestClient.bindToController(productController).build();
    }

    @Test
    void createProductShouldWork() {
        webTestClient.post()
                .uri("/products/create")
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errors").doesNotExist()
                .jsonPath("$.data").exists();
    }

    @Test
    void createProductNameExistsShouldFail() {
        Mockito.when(createProductUseCase.execute(Mockito.any()))
                .thenReturn(Mono.error(new RuntimeException("Product already exists")));
        webTestClient.post()
                .uri("/products/create")
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.errors").isArray()
                .jsonPath("$.errors[0]").isEqualTo("Product already exists")
                .jsonPath("$.data").doesNotExist();
    }
}