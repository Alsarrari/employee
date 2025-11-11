package com.springbooteventsystem.employeemanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Employee {
    @NotNull(message = "id cannot be null")
    @Size(min = 3 ,message = "ID length must be more than 2 characters")
    private String id;
    @NotNull(message = "name cannot be nul")
    @Size(min = 5,message = "Name length must be more than 4 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only characters (no numbers)")
    private String name;
    @Email(message = "Must be a valid email format")
    @NotNull(message = "email cannot be nul")
    private String email;
    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(
            regexp = "^05\\d{8}$",
            message = "Phone number must start with '05' and contain exactly 10 digits"
    )
    private String phoneNumber;
    @NotNull(message = "Age cannot be null")
    @Min(value = 25, message = "Age must be 25 or older")
    private Integer age;

    @NotNull(message = "Position cannot be null")
    @Pattern(regexp = "^(supervisor|coordinator)$", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Position must be either 'supervisor' or 'coordinator'")
    private String position;
    private Boolean onLeave = false;
    @PastOrPresent(message = "Hire date must be today or in the past")
    @NotNull(message = "Hire date cannot be null")
    private LocalDate hireDate;

    @NotNull(message = "Annual leave cannot be null")
    @Min(value = 0, message = "Annual leave must be a positive number")
    private Integer annualLeave;

}
