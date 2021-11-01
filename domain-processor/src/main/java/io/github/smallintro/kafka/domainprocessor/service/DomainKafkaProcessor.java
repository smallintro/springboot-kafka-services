package io.github.smallintro.kafka.domainprocessor.service;

import io.github.smallintro.kafka.domainprocessor.model.DomainInfo;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class DomainKafkaProcessor {

    @Bean
    public Function<KStream<String, DomainInfo>, KStream<String, DomainInfo>> domainProcessor() {
        System.out.println("[domainProcessor] started");
        return kStream -> kStream.filter((key, domain) -> {
            if (domain.isDead()) {
                System.out.println("Inactive Domain: " + domain.getDomain());
            } else {
                System.out.println("Active Domain: " + domain.getDomain());
            }
            return !domain.isDead();
        });
    }

}
