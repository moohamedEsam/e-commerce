package com.example.proudct.domain.usecase.create;

import com.example.proudct.domain.model.Product;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface CreateProductUseCase {
    Mono<String> execute(Product product);
}
