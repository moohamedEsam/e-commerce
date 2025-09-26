package com.example.product.application.repository;

import com.example.proudct.domain.model.Product;
import com.github.f4b6a3.ulid.UlidCreator;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.List;

@Table("product")
@Data
public class ProductEntity {
    String name;
    String description;
    BigDecimal price;
    String categoryId;
    List<String> images;
    @Id
    String id = UlidCreator.getUlid().toString();

    public Product toProduct() {
        var product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImages(images);
        return product;
    }

    public static ProductEntity fromProduct(Product product) {
        var entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setImages(product.getImages());
        return entity;
    }
}
