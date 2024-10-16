package com.example.ecommerce.kafka.database.consumer.kafka;

import com.example.ecommerce.kafka.database.consumer.domain.Order;
import com.example.ecommerce.kafka.database.consumer.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public KafkaConsumer(OrderService orderService, ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topicPartitions = {
                    @TopicPartition(topic = "orders", partitions = {"4"})
            },
            groupId = "ecommerce-group")
    public void consume(String orderJson) {
        try {
            Order order = objectMapper.readValue(orderJson, Order.class);
            orderService.save(order);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
