package com.example.product.application.controller;

import com.example.product.application.ProductApplication;
import com.example.product.application.repository.CachedProductRepositoryImpl;
import com.example.product.application.repository.PostgresProductRepository;
import com.example.product.application.repository.ProductEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProductApplication.class)
@ActiveProfiles("test")
class ProductControllerIntegrationTestCase {

    @Autowired
    PostgresProductRepository postgresProductRepository;

    @Autowired
    ReactiveRedisTemplate<String, ProductEntity> redisTemplate;
    @Autowired
    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        postgresProductRepository.deleteAll().block();
        redisTemplate.delete(CachedProductRepositoryImpl.PRODUCT_HASH_KEY).block();
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

        var entries = redisTemplate.opsForHash()
                .entries(CachedProductRepositoryImpl.PRODUCT_HASH_KEY)
                .collectList().block();
        assertThat(entries).hasSize(1);
        var product = postgresProductRepository.findByName("test").block();
        assertThat(product).isNotNull();
    }

    @AfterEach
    void tearDown() {
        postgresProductRepository.deleteAll().block();
        redisTemplate.delete(CachedProductRepositoryImpl.PRODUCT_HASH_KEY).block();
    }
}