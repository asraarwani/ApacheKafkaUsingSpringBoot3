package com.jpmchase.www.controller;

import com.jpmchase.www.dto.Employee;
import com.jpmchase.www.service.ApacheKafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private ApacheKafkaProducerService apacheKafkaProducerService;

    @GetMapping("/send/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        ResponseEntity<?> responseEntity = null;
        try {
            apacheKafkaProducerService.publishMessage(message);
            responseEntity = ResponseEntity.ok("Message sent.");
        } catch (Exception exception) {
            responseEntity = ResponseEntity.internalServerError().build();
        }
        return responseEntity;
    }

    @PostMapping("/send")
    public ResponseEntity<?> publishDetailedMessage(@RequestBody Employee employee) {
        ResponseEntity<?> responseEntity = null;
        try {
            apacheKafkaProducerService.publishDetailedMessage(employee);
            responseEntity = ResponseEntity.ok("Detailed message sent.");
        } catch (Exception exception) {
            responseEntity = ResponseEntity.internalServerError().build();
        }
        return responseEntity;
    }
}
