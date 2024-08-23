package com.task.student.model;

import java.time.LocalDate;
import java.time.Period;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, message = "First name must be at least 3 characters long")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 3, message = "Last name must be at least 3 characters long")
    private String lastName;

    @NotNull(message = "DOB is mandatory")
    private LocalDate dob;

    @Min(value = 0, message = "Marks should be between 0 and 100")
    @Max(value = 100, message = "Marks should be between 0 and 100")
    private Integer marks1;

    @Min(value = 0, message = "Marks should be between 0 and 100")
    @Max(value = 100, message = "Marks should be between 0 and 100")
    private Integer marks2;

    @Min(value = 0, message = "Marks should be between 0 and 100")
    @Max(value = 100, message = "Marks should be between 0 and 100")
    private Integer marks3;

    @Pattern(regexp = "A|B|C", message = "Section must be one of the following: A, B, C")
    private String section;

    @Pattern(regexp = "M|F", message = "Gender must be M or F")
    private String gender;

    private Integer totalMarks;
    private Double averageMarks;
    private String result;

    @PrePersist
    @PreUpdate
    private void validateAndCalculate() {
        validateAge();
        calculateTotalAndAverage();
        calculateResult();
    }

    private void validateAge() {
        if (dob != null) {
            int age = Period.between(dob, LocalDate.now()).getYears();
            if (age < 15 || age > 20) {
                throw new IllegalArgumentException(
                        "Age must be greater than 15 years and less than or equal to 20 years");
            }
        } else {
            throw new IllegalArgumentException("Date of birth is mandatory");
        }
    }

    private void calculateTotalAndAverage() {
        System.out.println(marks1);
        int total = (marks1 != null ? marks1 : 0) +
                (marks2 != null ? marks2 : 0) +
                (marks3 != null ? marks3 : 0);
        this.totalMarks = total;

        int subjectsCount = (marks1 != null ? 1 : 0) +
                (marks2 != null ? 1 : 0) +
                (marks3 != null ? 1 : 0);

        this.averageMarks = subjectsCount > 0 ? (double) total / subjectsCount : 0.0;
    }

    private void calculateResult() {
        if ((marks1 != null && marks1 < 35) ||
                (marks2 != null && marks2 < 35) ||
                (marks3 != null && marks3 < 35)) {
            this.result = "Fail";
        } else {
            this.result = "Pass";
        }
    }
}
