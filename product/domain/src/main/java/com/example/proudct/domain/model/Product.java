package com.example.proudct.domain.model;

import com.github.f4b6a3.ulid.UlidCreator;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    String name;
    String description;
    BigDecimal price;
    String categoryId;
    String id = UlidCreator.getUlid().toString();

    public static Product empty() {
        return new Product();
    }
}
