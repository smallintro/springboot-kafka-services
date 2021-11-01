package io.github.smallintro.kafka.orderprocessor.service;

import io.github.smallintro.kafka.orderprocessor.constant.KafkaConstants;
import io.github.smallintro.kafka.orderprocessor.model.OrderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaTopicProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaTopicProducer.class);
    @Autowired
    private KafkaTemplate<String, String> stringKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, OrderInfo> orderKafkaTemplate;

    public void sendStringMessage(String message, String requestId) {
        logger.info(String.format("send message for order with requestId %s", requestId));
        stringKafkaTemplate.send(KafkaConstants.TOPIC, message);
    }

    public void sendOrderMessage(OrderInfo order, String requestId) {
        logger.info(String.format("send order with requestId %s", requestId));
        ListenableFuture<SendResult<String, OrderInfo>> future = orderKafkaTemplate.send(KafkaConstants.TOPIC_JSON, order);

        /* Block thread to get the kafka response. Process further based on success or failure.
         This implementation will slow down the process.
         It is not recommended blocking the producer, because the Kafka is known as a fast stream processing platform.
         */
        future.addCallback(new ListenableFutureCallback<SendResult<String, OrderInfo>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send requestId=[ {} ] due to : {}", requestId, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, OrderInfo> result) {
                logger.info("Sent requestId=[ {} ] with offset=[ {} ]", requestId, result.getRecordMetadata().offset());
            }
        });
    }
}
