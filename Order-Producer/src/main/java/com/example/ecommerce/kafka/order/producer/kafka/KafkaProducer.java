package com.example.ecommerce.kafka.order.producer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class KafkaProducer {
    private static final String TOPIC = "orders";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(String orderJson) {

        int NUM_PARTITIONS = 5;

        for (int partition = 0; partition < NUM_PARTITIONS; partition++) {

            kafkaTemplate.send(TOPIC, partition, "", orderJson);
        }
    }

}
