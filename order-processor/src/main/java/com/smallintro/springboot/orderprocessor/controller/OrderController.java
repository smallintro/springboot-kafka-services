package com.smallintro.springboot.orderprocessor.controller;

import com.smallintro.springboot.orderprocessor.model.OrderInfo;
import com.smallintro.springboot.orderprocessor.kafka.KafkaTopicProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/messenger")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private KafkaTopicProducer kafkaProducer;

    @PutMapping("/kafka/{request_id}")
    public ResponseEntity publishStringMessage(@PathParam("request_id") String requestId,
                                               @RequestBody final String message) {
        logger.info(String.format("update message received on order with requestId %s",requestId));
        try {
            kafkaProducer.sendStringMessage(message, requestId);
            return new ResponseEntity("Success to update order with requestId: " + requestId, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Success to update order with requestId: " + requestId + " due to " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/kafka/{requestId}")
    public ResponseEntity publishOrderMessage(@PathParam("request_id") String requestId,
                                              @RequestBody final OrderInfo order) {
        logger.info(String.format("order received with requestId %s",requestId));
        try {
            kafkaProducer.sendOrderMessage(order, requestId);
            return new ResponseEntity("Success to place order with requestId: " + requestId, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Failed to place order with requestId: " + requestId + " due to " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
