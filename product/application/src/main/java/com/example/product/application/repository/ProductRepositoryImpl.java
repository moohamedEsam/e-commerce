package com.example.product.application.repository;

import com.example.product.application.mapper.ProductMapper;
import com.example.proudct.domain.model.Product;
import com.example.proudct.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    private final PostgresProductRepository postgresProductRepository;
    private final ProductMapper productMapper;

    public ProductRepositoryImpl(PostgresProductRepository postgresProductRepository, ProductMapper productMapper) {
        this.postgresProductRepository = postgresProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Mono<Product> findById(String id) {
        return postgresProductRepository.findById(id)
                .map(productMapper::toProduct);
    }

    @Override
    public Mono<Product> findByName(String name) {
        return postgresProductRepository.findByName(name)
                .map(productMapper::toProduct);
    }

    @Override
    public Mono<Product> save(Product product) {
        return postgresProductRepository.save(productMapper.fromProduct(product))
                .map(productMapper::toProduct);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return postgresProductRepository.deleteById(id);
    }
}
