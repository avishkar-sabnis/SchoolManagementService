package com.education.schoolmanagement.controller;

import com.education.schoolmanagement.Model.Student;
import com.education.schoolmanagement.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.Map;

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

    @GetMapping("/getAllStudents")
    public Map<Integer,Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PutMapping("/updateStudent/{studentId}")
    @Transactional
    public ResponseEntity<Student> updateStudent(@RequestBody Student updateStudent,
                                                 @PathVariable Integer studentId) {

        Student updatedStudent = studentService.updateStudent(updateStudent, studentId);

        if (updatedStudent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }

        return ResponseEntity.ok(updatedStudent);
    }


}
