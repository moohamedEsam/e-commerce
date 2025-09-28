package com.example.proudct.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    String name;
    String description;
    BigDecimal price;
    String categoryId;
    String id;

    public static Product empty() {
        return new Product("test", "test", BigDecimal.ONE, null, null);
    }
}
