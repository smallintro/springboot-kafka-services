package io.github.smallintro.kafka.orderprocessor.controller;

import io.github.smallintro.kafka.orderprocessor.model.OrderInfo;
import io.github.smallintro.kafka.orderprocessor.service.KafkaTopicProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messenger")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private KafkaTopicProducer kafkaProducer;

    @PutMapping("/kafka/{request_id}")
    public ResponseEntity publishStringMessage(@PathVariable("request_id") String requestId,
                                               @RequestBody final String message) {
        logger.info("update message received on order with requestId {}", requestId);
        try {
            kafkaProducer.sendStringMessage(message, requestId);
            return new ResponseEntity("Success to update order with requestId: " + requestId, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Failed to update order with requestId: " + requestId + " due to " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/kafka/{request_id}")
    public ResponseEntity publishOrderMessage(@PathVariable("request_id") String requestId,
                                              @RequestBody final OrderInfo order) {
        logger.info("order received on order with requestId {}", requestId);
        try {
            kafkaProducer.sendOrderMessage(order, requestId);
            return new ResponseEntity("Success to place order with requestId: " + requestId, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Failed to place order with requestId: " + requestId + " due to " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
