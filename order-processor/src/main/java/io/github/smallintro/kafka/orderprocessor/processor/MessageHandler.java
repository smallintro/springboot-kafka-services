package io.github.smallintro.kafka.orderprocessor.processor;

import org.springframework.stereotype.Component;

@Component
public interface MessageHandler<T> {
    public void handleMessage(T message);
}
