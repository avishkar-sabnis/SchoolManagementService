package com.education.schoolmanagement.controller;

import com.education.schoolmanagement.Model.PrototypeBean;
import com.education.schoolmanagement.Model.SingletonBean;
import com.education.schoolmanagement.Model.Student;
import com.education.schoolmanagement.Util.CacheInspectUtil;
import com.education.schoolmanagement.service.StudentService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/StudentManagement")
@Scope("prototype")
public class StudentController {

    StudentService studentService;
    CacheInspectUtil cacheInspectUtil;
    SingletonBean singletonBean;
    PrototypeBean prototypeBean;

    public StudentController(StudentService studentService, CacheInspectUtil cacheInspectUtil,
                             SingletonBean singletonBean, PrototypeBean prototypeBean) {
        this.studentService = studentService;
        this.cacheInspectUtil = cacheInspectUtil;
        this.singletonBean = singletonBean;
        this.prototypeBean = prototypeBean;

    }

    @PostConstruct
    public void init(){
        System.out.println("Controller Bean hashCode:" + this.hashCode());
        System.out.println("Singleton Bean Hashcode:"+ singletonBean.hashCode());
        System.out.println("protoype Bean Hashscode:"+ prototypeBean.hashCode());
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
        System.out.println("Get All students Controller");
        cacheInspectUtil.inspectAllCaches();

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
