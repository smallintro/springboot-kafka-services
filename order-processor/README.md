# java_message_service

1) Start Zookeeper node instance
> bin/zookeeper-server-start.sh config/zookeeper.properties

2) Start Kafka server
> bin/kafka-server-start.sh config/server.properties

3) Once kafka service is up create the kafka topic
> bin/kafka-topic.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka-message-topic

4) consuming test message
> bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic kafka-message-topic --from-beginning

4) produce test message
> bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic kafka-message-topic
> Hello Listeners