package com.example.product.application.repository;

import com.example.product.application.mapper.ProductMapper;
import com.example.proudct.domain.model.Product;
import com.example.proudct.domain.repository.ProductRepository;
import com.example.proudct.domain.usecase.create.CreateProductUseCase;
import com.example.proudct.domain.usecase.create.DefaultCreateProductUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataR2dbcTest
@ActiveProfiles("test")
class CreateProductUseCaseDbTestCase {
    @Autowired
    PostgresProductRepository postgresProductRepository;
    ProductRepository productRepository;

    CreateProductUseCase createProductUseCase;
    Product product = new Product("test", "test", BigDecimal.ONE, null, null);

    @BeforeEach
    void setup() {
        var mapper = Mappers.getMapper(ProductMapper.class);
        productRepository = new ProductRepositoryImpl(postgresProductRepository, mapper);
        createProductUseCase = new DefaultCreateProductUseCase(productRepository);
    }

    @Test
    void createProductShouldWork() {
        var result = createProductUseCase.execute(product).block();
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(product.getName());
        assertThat(result.getDescription()).isEqualTo(product.getDescription());
        assertThat(result.getPrice()).isEqualTo(product.getPrice());
        assertThat(result.getCategoryId()).isNull();
        product.setId(result.getId());
    }

    @AfterEach
    void tearDown() {
        postgresProductRepository.deleteById(product.getId()).block();
    }
}