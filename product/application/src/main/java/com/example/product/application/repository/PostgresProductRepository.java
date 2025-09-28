package com.example.product.application.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface PostgresProductRepository extends R2dbcRepository<ProductEntity, String> {
    Mono<ProductEntity> findByName(String name);
}
