package com.example.product.application.mapper;

import com.example.product.application.controller.dto.CreateProductRequestDto;
import com.example.product.application.repository.ProductEntity;
import com.example.proudct.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductEntity entity);

    ProductEntity fromProduct(Product product);

    CreateProductRequestDto toProduct(Product product);
}
