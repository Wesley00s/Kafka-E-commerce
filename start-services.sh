#!/bin/bash

BASE_DIR="../Kafka-E-commerce"

start_service() {
    SERVICE_DIR=$1
    SERVICE_NAME=$2

    echo "Initialing $SERVICE_NAME..."
    (cd "$BASE_DIR/$SERVICE_DIR" && ./gradlew bootRun) &

    sleep 4
}

start_service "Database-Consumer" "Database Consumer"

start_service "Notify-Consumer" "Notify Consumer"

start_service "Order-Producer" "Order Producer"

start_service "Payment-Consumer" "Payment Consumer"

start_service "Stock-Consumer" "Stock Consumer"

echo "All services is running!"
