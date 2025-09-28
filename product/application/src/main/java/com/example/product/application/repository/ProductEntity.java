package com.example.product.application.repository;

import com.github.f4b6a3.ulid.UlidCreator;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("product")
@Data
public class ProductEntity {
    String name;
    String description;
    BigDecimal price;
    String categoryId;
    @Id
    String id = UlidCreator.getUlid().toString();

}
