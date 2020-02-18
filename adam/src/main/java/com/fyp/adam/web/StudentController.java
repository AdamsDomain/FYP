package com.fyp.adam.web;

import com.fyp.adam.model.Student;
import com.fyp.adam.model.StudentRepository;
import com.fyp.adam.model.User;
import com.fyp.adam.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class StudentController {

    private final Logger log = LoggerFactory.getLogger(StudentController.class);
    private StudentRepository studentRepository;
    private UserRepository userRepository;

    public StudentController(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/students")
    Collection<Student> students(Principal principal) {

        return studentRepository.findAllByUserId(principal.getName());
    }

    @GetMapping("/student/{id}")
    ResponseEntity<?> getStudent(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/student")
    ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) throws URISyntaxException {
        log.info("Request to create group: {}", student);
        Student result = studentRepository.save(student);
        return ResponseEntity.created(new URI("/api/student/" + result.getId()))
                .body(result);
    }

    @PutMapping("/student/{id}")
    ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student) {
        log.info("Request to update student: {}", student);
        Student result = studentRepository.save(student);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        log.info("Request to delete student: {}", id);
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
