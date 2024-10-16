package com.example.ecommerce.kafka.stock.consumer.kafka;

import com.example.ecommerce.kafka.stock.consumer.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private Order orderNotification;

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
            orderNotification = objectMapper.readValue(orderJson, Order.class);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String getStockNotification() {
        int quantityProductStock = 50;
        if (orderNotification == null) {
            return "<div style='font-family: Arial, sans-serif; padding: 10px; border: 1px solid #ddd; border-radius: 8px; background-color: #f9f9f9; max-width: 400px;'>" +
                    "<h2 style='color: #4CAF50;'>Estoque</h2>" +
                    "<p><strong>Produto:</strong> Laptop</p>" +
                    "<p><strong>Quantidade:</strong> " + quantityProductStock + "</p>" +
                    "</div>";
        }

        return "<div style='font-family: Arial, sans-serif; padding: 10px; border: 1px solid #ddd; border-radius: 8px; background-color: #f9f9f9; max-width: 400px;'>" +
                "<h2 style='color: #4CAF50;'>Estoque Atualizado!</h2>" +
                "<p><strong>Produto:</strong> " + orderNotification.getProductName() + "</p>" +
                "<p><strong>Quantidade atualizada:</strong> " + (quantityProductStock - orderNotification.getQuantity()) + "</p>" +
                "<p>O estoque foi atualizado com sucesso!</p>" +
                "</div>";
    }
}
