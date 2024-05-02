package com.spring.task2;

import com.spring.task2.domain.Group;
import com.spring.task2.domain.Student;
import com.spring.task2.repository.GroupRepository;
import com.spring.task2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Task2Application {

    public static void main(String[] args) {
        SpringApplication.run(Task2Application.class, args);
    }
}
