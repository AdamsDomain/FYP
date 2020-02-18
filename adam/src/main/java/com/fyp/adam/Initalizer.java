package com.fyp.adam;

import com.fyp.adam.model.Event;
import com.fyp.adam.model.Student;
import com.fyp.adam.model.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final StudentRepository repository;

    public Initializer(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Adam", "John", "Leroy",
                "Frank").forEach(name ->
                repository.save(new Student(name, ""))
        );

        Student student = repository.findByName("Adam");
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2020-01-01T18:00:00.000Z"))
                .build();
        student.setEvents(Collections.singleton(e));
        repository.save(student);

        repository.findAll().forEach(System.out::println);
    }
}
