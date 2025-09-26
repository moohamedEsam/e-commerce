package com.example.product.application.repository;

import com.example.proudct.domain.model.Product;
import com.example.proudct.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    private final PostgresProductRepository postgresProductRepository;

    public ProductRepositoryImpl(PostgresProductRepository postgresProductRepository) {
        this.postgresProductRepository = postgresProductRepository;
    }

    @Override
    public Mono<Product> findById(String id) {
        return postgresProductRepository.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        return postgresProductRepository.save(product);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return postgresProductRepository.deleteById(id);
    }
}
