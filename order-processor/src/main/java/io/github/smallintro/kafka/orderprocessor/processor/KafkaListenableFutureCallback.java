package io.github.smallintro.kafka.orderprocessor.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class KafkaListenableFutureCallback<String, T> implements ListenableFutureCallback<SendResult<String, T>> {
    private static final Logger logger = LoggerFactory.getLogger(KafkaListenableFutureCallback.class);

    private String request_id;
    private String topicName;

    public KafkaListenableFutureCallback(String request_id, String topicName) {
        this.request_id = request_id;
        this.topicName = topicName;
    }

    @Override
    public void onFailure(Throwable ex) {
        logger.info("onFailure called");
        logger.info("Failed to send request: {} to topic: {} due to : {}", this.request_id, topicName, ex.getMessage());
    }

    @Override
    public void onSuccess(SendResult<String, T> result) {
        logger.info("onSuccess called");
        logger.info("Success to send request: {} to topic {} with offset=[ {} ]", this.request_id, topicName, result.getRecordMetadata().offset());
    }
}
