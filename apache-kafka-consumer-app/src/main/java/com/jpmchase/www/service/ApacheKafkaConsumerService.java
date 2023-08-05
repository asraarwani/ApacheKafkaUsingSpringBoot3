package com.jpmchase.www.service;


import com.jpmchase.www.dto.Employee;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ApacheKafkaConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void consumeMessage(String message) {
        System.out.println("Apache Kafka Consumer consumed message : " + message);
    }

    @KafkaListener(topics = "test-topic-detailed", groupId = "test-group")
    public void consumeDetailedMessage(Employee employee) {
        System.out.println("Apache Kafka Consumer consumed detailed message : " + employee);
    }

}
