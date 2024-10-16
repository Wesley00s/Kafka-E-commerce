package com.example.ecommerce.kafka.database.consumer.service;

import com.example.ecommerce.kafka.database.consumer.domain.Order;

public interface OrderService {
    Order save(Order order);
}
