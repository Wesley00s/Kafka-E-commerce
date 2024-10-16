package com.example.ecommerce.kafka.order.producer.controller;

import com.example.ecommerce.kafka.order.producer.domain.Order;
import com.example.ecommerce.kafka.order.producer.dto.OrderDTO;
import com.example.ecommerce.kafka.order.producer.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final KafkaProducer kafkaProducer;

    public OrderController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO orderDTO) {
        Order order = new Order(
                orderDTO.productName(),
                orderDTO.quantity(),
                orderDTO.price()
        );
        this.kafkaProducer.sendOrder(order.toJson());
        return ResponseEntity.ok(OrderDTO.fromDomain(order));
    }
}
