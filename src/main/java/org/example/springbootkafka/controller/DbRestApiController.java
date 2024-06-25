package org.example.springbootkafka.controller;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.example.springbootkafka.entities.Student;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DbRestApiController {
    private final LoadDataIntoDB ldb;
    private final KafkaTemplate<String, Student> template;
    private final NewTopic topic;

    @GetMapping("/send-to-kafka-broker")
    public void sendToKafka() {
        List<Student> list = ldb.fetchAll();
        list.stream().forEach(studentRecord -> template.send(topic.name(),
                studentRecord));
    }
}
