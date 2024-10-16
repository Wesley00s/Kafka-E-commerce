package com.example.ecommerce.kafka.database.consumer.service.serviceImp;

import com.example.ecommerce.kafka.database.consumer.domain.Order;
import com.example.ecommerce.kafka.database.consumer.domain.repository.OrderRepository;
import com.example.ecommerce.kafka.database.consumer.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
