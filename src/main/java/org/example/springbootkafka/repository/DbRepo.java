package org.example.springbootkafka.repository;

import org.example.springbootkafka.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbRepo extends JpaRepository<Student, Long> {

}
