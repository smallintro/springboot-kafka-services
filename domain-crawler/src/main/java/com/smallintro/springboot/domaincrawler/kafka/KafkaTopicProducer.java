package com.smallintro.springboot.domaincrawler.kafka;

import com.smallintro.springboot.domaincrawler.constant.KafkaConstants;
import com.smallintro.springboot.domaincrawler.model.DomainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaTopicProducer {
    @Autowired
    private KafkaTemplate<String, DomainInfo> kafkaTemplate;

    public void publishTopic(DomainInfo domain) {
        System.out.println("publish topic for domain: "+domain.getDomain());
        kafkaTemplate.send(KafkaConstants.ACTIVE_WEB_TOPIC, domain);
    }

}
