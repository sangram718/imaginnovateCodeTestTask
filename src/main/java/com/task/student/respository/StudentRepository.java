package com.task.student.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.student.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
