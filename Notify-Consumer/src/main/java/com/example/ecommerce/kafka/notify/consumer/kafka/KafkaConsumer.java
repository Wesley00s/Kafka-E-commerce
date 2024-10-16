package com.example.ecommerce.kafka.notify.consumer.kafka;

import com.example.ecommerce.kafka.notify.consumer.domain.Order;
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
                    @TopicPartition(topic = "orders", partitions = {"3"})
            },
            groupId = "ecommerce-group")
    public void consume(String orderJson) {
        try {
            orderNotification = objectMapper.readValue(orderJson, Order.class);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String getNotification() {
        if (orderNotification == null) {
            return "<h3 style='color:red;'>Nenhuma notificação de pedido disponível no momento.</h3>";
        }
        return "<div style='font-family: Arial, sans-serif; padding: 10px; border: 1px solid #ddd; border-radius: 8px; background-color: #f9f9f9; max-width: 400px;'>" +
                "<h2 style='color: #4CAF50;'>Compra Realizada com Sucesso!</h2>" +
                "<p><strong>Produto:</strong> " + orderNotification.getProductName() + "</p>" +
                "<p><strong>Valor Total:</strong> R$" + orderNotification.getTotalAmount() + "</p>" +
                "<p>Obrigado por comprar conosco!</p>" +
                "</div>";
    }
}
