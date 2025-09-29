package com.example.proudct.domain.event;

public interface ProductEventPublisher {
    void newProductCreated(String productId);
}
