package com.example.product.application.configuration;

import com.example.proudct.domain.annotation.UseCase;
import com.example.proudct.domain.model.Product;
import com.example.proudct.domain.usecase.create.CreateProductUseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackageClasses = {Product.class, CreateProductUseCase.class},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {UseCase.class})},
        useDefaultFilters = false
)
public class DomainConfiguration {
}
