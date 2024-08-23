package com.task.student.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.task.student.model.ErrorResponse;
import com.task.student.model.Student;
import com.task.student.service.StudentService;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student) {
        // Validate DOB
        if (student.getDob() != null) {
            int age = Period.between(student.getDob(), LocalDate.now()).getYears();
            if (age < 15 || age > 20) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Age must be between 15 and 20 years."));
            }
        }

        Student savedStudent = studentService.createStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping("student/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateStudent(@PathVariable Integer id,
            @RequestParam Integer marks1,
            @RequestParam Integer marks2,
            @RequestParam Integer marks3) {

        if (marks1 == null || marks2 == null || marks3 == null) {
            System.out.println("marks");
            return ResponseEntity.badRequest().body(new ErrorResponse("All marks are mandatory."));
        }
        if (marks1 < 0 || marks1 > 100 || marks2 < 0 || marks2 > 100 || marks3 < 0 || marks3 > 100) {
            System.out.println("marks");
            return ResponseEntity.badRequest().body(new ErrorResponse("Marks must be between 0 and 100."));
        }

        return new ResponseEntity<>(studentService.updateStudent(id, marks1, marks2, marks3), HttpStatus.CREATED);
    }
}
