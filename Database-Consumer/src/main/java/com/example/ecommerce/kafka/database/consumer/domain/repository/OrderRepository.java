package com.example.ecommerce.kafka.database.consumer.domain.repository;

import com.example.ecommerce.kafka.database.consumer.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
