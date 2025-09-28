package com.example.proudct.domain.usecase.create;

import com.example.proudct.domain.model.Product;
import com.example.proudct.domain.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseTest {
    CreateProductUseCase createProductUseCase;
    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        createProductUseCase = new DefaultCreateProductUseCase(productRepository);
    }

    @Test
    void shouldCreateProduct() {
        Mockito.when(productRepository.save(any()))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        Mockito.when(productRepository.findByName(any()))
                .thenReturn(Mono.empty());

        var result = createProductUseCase.execute(Product.empty()).block();
        assertThat(result).isNotNull();
    }

    @Test
    void productNameExistsShouldReturnError() {
        Mockito.when(productRepository.findByName(any()))
                .thenReturn(Mono.just(Product.empty()));
        Mono<Product> execute = createProductUseCase.execute(Product.empty());
        Assertions.assertThrows(RuntimeException.class, execute::block);

    }

}