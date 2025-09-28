package com.example.product.application.controller;

import com.example.proudct.domain.model.Product;
import com.example.proudct.domain.usecase.create.CreateProductUseCase;
import com.example.shared.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CreateProductUseCase createProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping("/create")
    public Mono<Result<Product>> create() {
        return createProductUseCase.execute(Product.empty())
                .map(Result::success)
                .onErrorResume(ex -> Mono.just(Result.failure(ex)));
    }
}
