package com.example.product.application.configuration;

import com.example.proudct.domain.annotation.UseCase;
import com.example.proudct.domain.model.Product;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackageClasses = {Product.class},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {UseCase.class})}
)
public class DomainConfiguration {
}
