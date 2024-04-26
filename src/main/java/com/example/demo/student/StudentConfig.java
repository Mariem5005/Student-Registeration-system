package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentsRepository repository){
        return args -> {
            Student Mariem= new Student(LocalDate.of(2004, MAY,1),"Mariemfly@@","Mariem1");
            Student Mahmoud= new Student(LocalDate.of(2004, MAY,1),"Ma@@","Mariem2");
            repository.saveAll(List.of(Mariem,Mahmoud));
        };
    }
}
