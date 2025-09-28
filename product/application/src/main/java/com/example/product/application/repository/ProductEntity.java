package com.example.product.application.repository;

import com.github.f4b6a3.ulid.UlidCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductEntity implements Persistable<String> {
     String name;
     String description;
     BigDecimal price;
     String categoryId;
    @Id
    String id;

    @Override
    public boolean isNew() {
        if (id == null) {
            id = UlidCreator.getUlid().toString();
            return true;
        }
        return false;
    }
}
