#!/bin/bash

docker compose up -d

echo "Waiting Kafka initialization..."
for i in {5..1}
do
  echo "$i sec left..."
  sleep 1
done

./create-topic.sh
./start-services.sh