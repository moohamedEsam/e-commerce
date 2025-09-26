package com.example.product.application.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CreateProductRequestDto {
    @NotBlank
    @Size(min = 5)
    String name;
    String description;
    @Min(1)
    BigDecimal price;
    String categoryId;
}
