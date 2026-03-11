package com.education.schoolmanagement.service;

import com.education.schoolmanagement.Model.Student;
import com.education.schoolmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student insertStudent(Student student){
        return studentRepository.save(student);
    }

}
