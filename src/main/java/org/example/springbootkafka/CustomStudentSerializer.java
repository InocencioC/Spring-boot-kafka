package org.example.springbootkafka;

import java.util.Map;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springbootkafka.entities.Student;

public class CustomStudentSerializer implements Serializer<Student> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure (Map<String, ?> configs, boolean iskey) {
    }

    @Override
    public byte[] serialize (String topic, Student data) {
        try {
            if (data == null ) {
                System.out.println("Null received at serialization");
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serialization MessageDto to byte[]");
        }
    }
    @Override
    public void close()  {
    }
}
