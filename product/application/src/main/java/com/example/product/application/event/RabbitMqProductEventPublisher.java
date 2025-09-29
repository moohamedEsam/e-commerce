package com.example.product.application.event;


import com.example.product.application.configuration.RabbitMqConfiguration;
import com.example.proudct.domain.event.ProductEventPublisher;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public record RabbitMqProductEventPublisher(
        AmqpTemplate amqpTemplate
) implements ProductEventPublisher {
    @Override
    public void newProductCreated(String productId) {
        amqpTemplate.convertAndSend(
                RabbitMqConfiguration.EXCHANGE_NAME,
                "",
                productId);
    }
}
