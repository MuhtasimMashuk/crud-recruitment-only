package com.bjit.recruitment.entity;


import com.bjit.recruitment.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity class
 * @author Muhtaim-Ul-Alam
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="RCT_EMP")
public class Employee extends BaseEntity{

    @Column(nullable=false, length=35)
    private String name;

    @Column(name="FATHER_NAME" , nullable=false, length=35)
    private String fatherName;

    @Column(name="MOTHER_NAME" , nullable=false, length=35)
    private String motherName;

    /**
     * dob indicates date of birth
     */
    @Column()
    private LocalDate dob;

    /**
     * doj indicates date of join
     */
    @Column()
    private LocalDate doj;

    @Enumerated(EnumType.STRING)
    @Column(length = 6, nullable = false)
    private Gender gender;
}

