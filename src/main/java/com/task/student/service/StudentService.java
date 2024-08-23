package com.task.student.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.task.student.model.Student;
import com.task.student.respository.StudentRepository;

import jakarta.validation.Valid;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudentMarks(@Valid Integer id, Integer marks1, Integer marks2, Integer marks3) {

        Optional<Student> existingStudent = studentRepository.findById(id);
        Student student = existingStudent.get();
        if (existingStudent.isPresent()) {
            student.setMarks1(marks1);
            student.setMarks2(marks2);
            student.setMarks3(marks3);

            int total = (marks1 != null ? marks1 : 0) +
                    (marks2 != null ? marks2 : 0) +
                    (marks3 != null ? marks3 : 0);
            student.setTotalMarks(total);

            int subjectsCount = (marks1 != null ? 1 : 0) +
                    (marks2 != null ? 1 : 0) +
                    (marks3 != null ? 1 : 0);

            student.setAverageMarks(subjectsCount > 0 ? (double) total / subjectsCount : 0.0);

            if ((marks1 != null && marks1 < 35) ||
                    (marks2 != null && marks2 < 35) ||
                    (marks3 != null && marks3 < 35)) {
                student.setResult("Fail");
            } else {
                student.setResult("Pass");
            }
        }
        return studentRepository.save(student);
    }

}
