package com.example.product.application.repository;

import com.example.proudct.domain.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostgresProductRepository extends ReactiveCrudRepository<Product, String> {
}
