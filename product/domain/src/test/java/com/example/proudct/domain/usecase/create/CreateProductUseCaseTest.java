package com.example.proudct.domain.usecase.create;

import com.example.proudct.domain.event.ProductEventPublisher;
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
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseTest {
    CreateProductUseCase createProductUseCase;
    @Mock
    ProductRepository productRepository;
    @Mock
    ProductEventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        createProductUseCase = new DefaultCreateProductUseCase(productRepository, eventPublisher);
    }

    @Test
    void shouldCreateProductAndSendEvent() {
        Mockito.when(productRepository.save(any()))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        Mockito.when(productRepository.findByName(any()))
                .thenReturn(Mono.empty());
        Mockito.doNothing().when(eventPublisher).newProductCreated(anyString());

        Product product = Product.empty();
        product.setId("1");
        var result = createProductUseCase.execute(product).block();
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        Mockito.verify(eventPublisher).newProductCreated(result.getId());
    }

    @Test
    void productNameExistsShouldReturnError() {
        Mockito.when(productRepository.findByName(any()))
                .thenReturn(Mono.just(Product.empty()));
        Mono<Product> execute = createProductUseCase.execute(Product.empty());
        Assertions.assertThrows(RuntimeException.class, execute::block);
        Mockito.verifyNoInteractions(eventPublisher);
    }

}