package com.example.product.application.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PostgresProductRepository extends ReactiveCrudRepository<ProductEntity, String> {
    Mono<ProductEntity> findByName(String name);
}
