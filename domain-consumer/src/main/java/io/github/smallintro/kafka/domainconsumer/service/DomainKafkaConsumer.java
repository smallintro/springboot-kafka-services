package io.github.smallintro.kafka.domainconsumer.service;

import io.github.smallintro.kafka.domainconsumer.model.DomainInfo;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DomainKafkaConsumer {

    @Bean
    public Consumer<KStream<String, DomainInfo>> domainConsumer() {
        return kStream -> kStream.foreach((key, domain) -> {
            System.out.println(String.format("Domain {%s} is in {%s} state", domain.getDomain(), domain.isDead() ? "Inactive" : "Active"));
        });
    }
}
