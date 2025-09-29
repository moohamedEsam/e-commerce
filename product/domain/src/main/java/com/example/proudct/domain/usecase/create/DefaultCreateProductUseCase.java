package com.example.proudct.domain.usecase.create;

import com.example.proudct.domain.annotation.UseCase;
import com.example.proudct.domain.event.ProductEventPublisher;
import com.example.proudct.domain.model.Product;
import com.example.proudct.domain.repository.ProductRepository;
import reactor.core.publisher.Mono;

@UseCase
public record DefaultCreateProductUseCase(
        ProductRepository productRepository,
        ProductEventPublisher eventPublisher) implements CreateProductUseCase {


    @Override
    public Mono<Product> execute(Product product) {
        return productRepository
                .findByName(product.getName())
                .flatMap(_ -> Mono.<Product>error(new RuntimeException("Product already exists")))
                .switchIfEmpty(Mono.defer(() -> createProduct(product)));

    }

    private Mono<Product> createProduct(Product product) {
        return productRepository.save(product)
                .doOnSuccess(x -> eventPublisher.newProductCreated(x.getId()));
    }
}
