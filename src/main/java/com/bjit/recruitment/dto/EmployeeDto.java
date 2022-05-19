package com.bjit.recruitment.dto;

import com.bjit.recruitment.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

    private String name;

    private String fatherName;

    private String motherName;

    private LocalDate dob;

    private LocalDate doj;

    private Gender gender;


}
