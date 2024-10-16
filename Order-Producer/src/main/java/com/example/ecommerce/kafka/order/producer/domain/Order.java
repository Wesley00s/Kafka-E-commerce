package com.example.ecommerce.kafka.order.producer.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.Random;

@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private String productName;
    private int quantity;
    private double price;
    private double totalAmount;

    public void calculateTotalAmount() {
        this.totalAmount = this.price * this.quantity;
    }

    public Order(String productName, int quantity, double price) {
        this.id = new Random().nextLong(0, 1000000000);
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        calculateTotalAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String info() {
        return """
                Product Name: %s
                Quantity: %d
                Price: %.2f
                Total Amount: %.2f
                """.formatted(productName, quantity, price, totalAmount);
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
