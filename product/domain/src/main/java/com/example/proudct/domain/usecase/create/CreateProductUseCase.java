package com.example.proudct.domain.usecase.create;

import com.example.proudct.domain.model.Product;
import reactor.core.publisher.Mono;

public interface CreateProductUseCase {
    Mono<Product> execute(Product product);
}
