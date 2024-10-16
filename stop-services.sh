#!/bin/bash

echo "Parando todos os serviços..."

SERVICES=("Database-Consumer" "Notify-Consumer" "Order-Producer" "Payment-Consumer" "Stock-Consumer")

for SERVICE in "${SERVICES[@]}"; do
    echo "Procurando processo para $SERVICE..."

    PID=$(ps -ef | grep "$SERVICE" | grep -v "grep" | awk '{print $2}')

    if [ -n "$PID" ]; then
        echo "Parando $SERVICE com PID $PID..."
        kill -9 $PID
        echo "$SERVICE parado."
    else
        echo "Nenhum processo encontrado para $SERVICE."
    fi
done

echo "Todos os serviços foram parados!"
