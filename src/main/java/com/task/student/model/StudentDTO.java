package com.task.student.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

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

    // Getters and Setters
}
