package com.example.ecommerce.kafka.stock.consumer.controller;

import com.example.ecommerce.kafka.stock.consumer.kafka.KafkaConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockNotificationController {

    private final KafkaConsumer kafkaConsumer;

    public StockNotificationController(KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @GetMapping(value = "/stock", produces = "text/html")
    public String getStockNotificationHtml() {
        return kafkaConsumer.getStockNotification();
    }
}
