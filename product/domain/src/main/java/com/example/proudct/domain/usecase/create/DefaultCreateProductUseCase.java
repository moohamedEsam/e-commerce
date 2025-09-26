package com.example.proudct.domain.usecase.create;

import com.example.proudct.domain.model.Product;
import com.example.proudct.domain.repository.ProductRepository;
import reactor.core.publisher.Mono;

public class DefaultCreateProductUseCase implements CreateProductUseCase {
    private final ProductRepository productRepository;

    public DefaultCreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Mono<String> execute(Product product) {
        productRepository.save(product);
        return Mono.just(product.getId());
    }
}
