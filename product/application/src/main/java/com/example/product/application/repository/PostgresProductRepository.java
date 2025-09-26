package com.example.product.application.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostgresProductRepository extends ReactiveCrudRepository<ProductEntity, String> {
}
