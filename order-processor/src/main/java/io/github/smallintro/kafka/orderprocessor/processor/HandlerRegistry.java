package io.github.smallintro.kafka.orderprocessor.processor;

import io.github.smallintro.kafka.orderprocessor.constant.KafkaConstants;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HandlerRegistry<T> {
    private Map<String, MessageHandler<T>> handlerMap = new HashMap<>();

    public void register(String handlerName, MessageHandler<T> handler){
        handlerMap.put(handlerName, handler);
    }

    public MessageHandler<T> getHandler(String handlerName){
        return handlerMap.getOrDefault(handlerName, handlerMap.get(KafkaConstants.Handler.DEFAULT_MESSAGE_HANDLER));
    }
}
