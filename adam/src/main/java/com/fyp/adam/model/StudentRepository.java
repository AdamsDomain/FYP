package com.fyp.adam.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);

    List<Student> findAllByUserId(String id);
}