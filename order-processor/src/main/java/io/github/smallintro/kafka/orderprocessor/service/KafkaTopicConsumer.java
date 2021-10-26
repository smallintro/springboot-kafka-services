package io.github.smallintro.kafka.orderprocessor.service;

import io.github.smallintro.kafka.orderprocessor.constant.KafkaConstants;
import io.github.smallintro.kafka.orderprocessor.processor.HandlerRegistry;
import io.github.smallintro.kafka.orderprocessor.processor.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaTopicConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaTopicConsumer.class);

    @Autowired
    private HandlerRegistry handlerRegistry;

    @KafkaListener(topics = {KafkaConstants.TOPIC}, groupId = KafkaConstants.GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void messageConsumer(String message) {
        processMessage(KafkaConstants.Handler.DEFAULT_MESSAGE_HANDLER, message);
    }

    @KafkaListener(topics = {KafkaConstants.TOPIC_JSON}, groupId = KafkaConstants.GROUP_ID_JSON, containerFactory = "orderKafkaListenerContainerFactory")
    public void orderConsumer(Object orderInfo) {
        processMessage(KafkaConstants.Handler.ORDER_MESSAGE_HANDLER, orderInfo);
    }

    public void processMessage(String handlerName, Object message) {
        MessageHandler messageHandler = handlerRegistry.getHandler(handlerName);
        messageHandler.handleMessage(message);
    }

}
