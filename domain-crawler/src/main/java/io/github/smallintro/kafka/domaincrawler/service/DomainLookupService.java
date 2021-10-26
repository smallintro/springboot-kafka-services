package io.github.smallintro.kafka.domaincrawler.service;

import io.github.smallintro.kafka.domaincrawler.model.DomainList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DomainLookupService {

    private String DOMAIN_DB_API = "https://api.domainsdb.info/v1/domains/search?domain=";

    @Autowired
    private KafkaTopicProducer kafkaProducer;

    public void lookupDomain(String domainName, String zone) {
        String domainLookupURI = DOMAIN_DB_API + domainName;
        if (null != zone && !"".equals(zone.trim())) {
            domainLookupURI = domainLookupURI + "&zone=" + zone;
        }
        System.out.println("domainLookupURI: "+domainLookupURI);
        Mono<DomainList> domainListMono = WebClient.create()
                .get()
                .uri(domainLookupURI)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DomainList.class);

        // subscribe and consume messages from mono
        // iterate and publish each domain info to kafka topic
        domainListMono.subscribe(domainList -> {
            domainList.getDomains().forEach(domainInfo -> {
                kafkaProducer.publishTopic(domainInfo);
            });
        });
    }
}
