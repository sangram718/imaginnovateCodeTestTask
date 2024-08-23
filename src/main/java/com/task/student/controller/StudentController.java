package com.task.student.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.task.student.model.Student;
import com.task.student.service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("student/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@Valid @PathVariable Integer id,
            @RequestParam @NotNull @Min(value = 0, message = "Marks should be between 0 and 100") @Max(value = 100, message = "Marks should be between 0 and 100") Integer marks1,
            @RequestParam @NotNull @Min(value = 0, message = "Marks should be between 0 and 100") @Max(value = 100, message = "Marks should be between 0 and 100") Integer marks2,
            @RequestParam @NotNull @Min(value = 0, message = "Marks should be between 0 and 100") @Max(value = 100, message = "Marks should be between 0 and 100") Integer marks3) {

        if (marks1 == null || marks2 == null || marks3 == null) {
            throw new IllegalArgumentException("All marks are mandatory");
        }
        if (marks1 < 0 || marks1 > 100 || marks2 < 0 || marks2 > 100 || marks3 < 0 || marks3 > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }

        return studentService.updateStudent(id, marks1, marks2, marks3);
    }
}
