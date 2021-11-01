package io.github.smallintro.kafka.orderprocessor.processor;

import io.github.smallintro.kafka.orderprocessor.constant.KafkaConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DefaultMessageHandler<T> implements MessageHandler<T> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultMessageHandler.class);

    @Autowired
    private HandlerRegistry handlerRegistry;

    @PostConstruct
    public void register() {
        handlerRegistry.register(KafkaConstants.Handler.DEFAULT_MESSAGE_HANDLER, this);
    }

    @Override
    public void handleMessage(T message) {
        logger.info("{} received message: {}", KafkaConstants.Handler.DEFAULT_MESSAGE_HANDLER, message);
    }

}
