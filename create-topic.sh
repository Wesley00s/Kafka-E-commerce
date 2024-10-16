#!/bin/bash
docker exec -it kafka-e-commerce-kafka-1 /opt/bitnami/kafka/bin/kafka-topics.sh --create --topic orders --bootstrap-server localhost:9092 --partitions 5 --replication-factor 1

