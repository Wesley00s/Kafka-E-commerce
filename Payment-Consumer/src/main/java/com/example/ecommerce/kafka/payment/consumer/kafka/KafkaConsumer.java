package com.example.ecommerce.kafka.payment.consumer.kafka;

import com.example.ecommerce.kafka.payment.consumer.domain.Order;
import com.example.ecommerce.kafka.payment.consumer.domain.Payment;
import com.example.ecommerce.kafka.payment.consumer.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    public KafkaConsumer(PaymentService paymentService, ObjectMapper objectMapper) {
        this.paymentService = paymentService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topicPartitions = {
                    @TopicPartition(topic = "orders", partitions = {"0"})
            },
            groupId = "ecommerce-group")
    public void consume(String orderJson) {
        try {
            System.out.println("Processando pagamento para o pedido...");

            Order order = objectMapper.readValue(orderJson, Order.class);

            Payment payment = new Payment();
            payment.setOrderId(order.getId());
            payment.setAmount(order.getTotalAmount());

            System.out.println("Pagamento salvo no banco de dados.");
            paymentService.create(payment);
        } catch (Exception e) {
            System.out.println("Erro ao processar o pedido: " + e.getMessage());
        }
    }
}
