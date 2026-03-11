package com.education.schoolmanagement.controller;

import com.education.schoolmanagement.Model.Student;
import com.education.schoolmanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/StudentManagement")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/insertStudent")
    public ResponseEntity<Student> insertStudent(@RequestBody Student student) {

        Student savedStudent = studentService.insertStudent(student);

        if (savedStudent == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student not inserted");
        }
        return ResponseEntity.ok(savedStudent);
    }


}
