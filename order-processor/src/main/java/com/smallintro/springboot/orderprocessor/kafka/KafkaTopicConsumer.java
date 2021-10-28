package com.smallintro.springboot.orderprocessor.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smallintro.springboot.orderprocessor.constant.KafkaConstants;
import com.smallintro.springboot.orderprocessor.model.OrderInfo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaTopicConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaTopicConsumer.class);
    @KafkaListener(topics = {KafkaConstants.TOPIC}, groupId = KafkaConstants.GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void consumer(String message) {
        logger.info(String.format("received message: %s",message));
    }

    @KafkaListener(topics = {KafkaConstants.TOPIC_JSON}, groupId = KafkaConstants.GROUP_ID_JSON, containerFactory = "orderKafkaListenerContainerFactory")
    public void orderConsumer(OrderInfo orderInfo) {
        logger.info(String.format("received order: %s",orderInfo));
    }

}
