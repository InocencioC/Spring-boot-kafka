package org.example.springbootkafka.controller;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.example.springbootkafka.entities.Student;
import org.example.springbootkafka.service.LoadDataIntoDB;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@AllArgsConstructor
public class DbRestApiController {
    private final LoadDataIntoDB ldb;
    private final KafkaTemplate<String, Student> template;
    private final NewTopic topic;

    @GetMapping("/save")
    public void save() {
        System.out.println("---> Starting Synchronous data load at "+ Instant.now());
        ldb.saveData();
        System.out.println("---> Synchronous data load completed at "+Instant.now());
    }

    @GetMapping("/send-to-kafka-broker")
    public void sendToKafka() {
        List<Student> list = ldb.fetchAll();
        list.stream().forEach(studentRecord -> template.send(topic.name(),studentRecord));
    }
}
