package com.example.ecommerce.kafka.payment.consumer.service;

import com.example.ecommerce.kafka.payment.consumer.domain.Payment;

public interface PaymentService {
    Payment create(Payment payment);
}
