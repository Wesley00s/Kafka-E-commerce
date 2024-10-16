package com.example.ecommerce.kafka.stock.consumer.kafka;

import com.example.ecommerce.kafka.stock.consumer.domain.Order;
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
                    @TopicPartition(topic = "orders", partitions = {"1"})
            },
            groupId = "ecommerce-group")
    public void consume(String orderJson) {
        try {
            System.out.println("Processando pagamento para o pedido...");
            Order order = objectMapper.readValue(orderJson, Order.class);

            System.out.println("Estoque atualizado pra o produto: " + order.getProductName());
        } catch (Exception e) {
            System.out.println("Erro ao processar o pedido: " + e.getMessage());
        }
    }
}
