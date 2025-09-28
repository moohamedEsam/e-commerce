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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataR2dbcTest
@ActiveProfiles("test")
class CreateProductUseCaseDbTestCase {
    @Autowired
    PostgresProductRepository postgresProductRepository;

    ProductRepository productRepository;

    CreateProductUseCase createProductUseCase;
    Product product = Product.empty();

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
        System.out.println(result);
    }

    @AfterEach
    void tearDown() {
        postgresProductRepository.deleteById(product.getId());
    }
}