package com.example.ecommerce.kafka.notify.consumer.kafka;

import com.example.ecommerce.kafka.notify.consumer.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    public KafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topicPartitions = {
                    @TopicPartition(topic = "orders", partitions = {"3"})
            },
            groupId = "ecommerce-group")
    public void consume(String orderJson) {
        try {
            System.out.println("Processando notificação do pedido...");
            Order order = objectMapper.readValue(orderJson, Order.class);

            System.out.println("Sua compra no valor de R$" + order.getTotalAmount() + " do produto " + order.getProductName()
            + "foi realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao processar o pedido: " + e.getMessage());
        }
    }
}
