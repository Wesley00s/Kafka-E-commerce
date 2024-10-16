package com.example.ecommerce.kafka.notify.consumer.controller;

import com.example.ecommerce.kafka.notify.consumer.domain.Order;
import com.example.ecommerce.kafka.notify.consumer.kafka.KafkaConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    private final KafkaConsumer kafkaConsumer;

    public NotificationController(KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @GetMapping("/notifications")
    public String getNotifications() {
        return kafkaConsumer.getNotification();
    }
}
