package com.jpmchase.www.service;

import com.jpmchase.www.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ApacheKafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishMessage(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test-topic", message);
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println("Message [ " + message + " ] sent with offset [ " + result.getRecordMetadata().offset() + " ] and partition [ " + result.getRecordMetadata().partition() + " ]");
            } else {
                System.out.println("Unable to send message due to [ " + exception.getMessage() + " ]");
            }
        });
    }

    public void publishDetailedMessage(Employee employee) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test-topic-detailed", employee);
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println("Message [ " + employee.toString() + " ] sent with offset [ " + result.getRecordMetadata().offset() + " ] and partition [ " + result.getRecordMetadata().partition() + " ]");
            } else {
                System.out.println("Unable to send detailed message due to [ " + exception.getMessage() + " ]");
            }
        });
    }
}
