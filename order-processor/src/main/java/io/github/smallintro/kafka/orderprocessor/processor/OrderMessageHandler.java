package io.github.smallintro.kafka.orderprocessor.processor;

import io.github.smallintro.kafka.orderprocessor.constant.KafkaConstants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OrderMessageHandler<T> implements MessageHandler<T> {
    private static final Logger logger = LoggerFactory.getLogger(OrderMessageHandler.class);

    @Autowired
    private HandlerRegistry handlerRegistry;

    @PostConstruct
    public void register() {
        handlerRegistry.register(KafkaConstants.Handler.ORDER_MESSAGE_HANDLER, this);
    }

    @Override
    public void handleMessage(T message) {
        logger.info("{} received message: {}", KafkaConstants.Handler.ORDER_MESSAGE_HANDLER, ((ConsumerRecord) message).value());
    }

}
