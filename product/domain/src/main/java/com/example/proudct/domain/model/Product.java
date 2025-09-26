package com.example.proudct.domain.model;

import com.github.f4b6a3.ulid.UlidCreator;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Product {
    String name;
    String description;
    BigDecimal price;
    String categoryId;
    List<String> images;
    String id = UlidCreator.getUlid().toString();

    public static Product empty() {
        return new Product();
    }
}
