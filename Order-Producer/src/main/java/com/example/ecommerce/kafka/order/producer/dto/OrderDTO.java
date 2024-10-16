package com.example.ecommerce.kafka.order.producer.dto;

import com.example.ecommerce.kafka.order.producer.domain.Order;

public record OrderDTO (
        String productName,
        int quantity,
        double price
) {
    public Order toDomain() {
        return new Order(
                productName,
                quantity,
                price
        );
    }

    public static OrderDTO fromDomain(Order order) {
        return new OrderDTO(order.getProductName(), order.getQuantity(), order.getPrice());
    }
}
