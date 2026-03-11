package com.education.schoolmanagement.Model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "student", schema = "schoolmanagement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column(name = "studentName")
    private String studentName;

    @Column(name = "student_address")
    private String studentAddress;

    @Column(name = "studentMarks")
    private Double studentMarks;

}