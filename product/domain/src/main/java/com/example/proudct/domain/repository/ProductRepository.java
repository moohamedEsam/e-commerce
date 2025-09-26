package com.example.proudct.domain.repository;

import com.example.proudct.domain.model.Product;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> findById(String id);
    Mono<Product> save(Product product);
    Mono<Void> deleteById(String id);
    Mono<Product> update(Product product);
}
