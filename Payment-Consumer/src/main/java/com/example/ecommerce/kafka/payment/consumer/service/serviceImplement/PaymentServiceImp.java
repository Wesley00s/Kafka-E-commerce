package com.example.ecommerce.kafka.payment.consumer.service.serviceImplement;

import com.example.ecommerce.kafka.payment.consumer.domain.Payment;
import com.example.ecommerce.kafka.payment.consumer.domain.PaymentRepository;
import com.example.ecommerce.kafka.payment.consumer.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImp(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }
}
