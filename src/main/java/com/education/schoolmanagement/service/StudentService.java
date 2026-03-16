package com.education.schoolmanagement.service;

import com.education.schoolmanagement.Model.Student;
import com.education.schoolmanagement.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student insertStudent(Student student){
        return studentRepository.save(student);
    }

    public Map<Integer,Student> getAllStudents(){

        List<Student> studentList = studentRepository.findAll();
        Map<Integer,Student> allStudentsMap = new HashMap<>();
        for(int i=0;i<studentList.size();i++){
            allStudentsMap.put(i,studentList.get(i));
        }
        return allStudentsMap;
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    //@Transactional(Transactional.TxType.REQUIRED)
    //@Transactional(Transactional.TxType.REQUIRES_NEW)
    //@Transactional(Transactional.TxType.NEVER)
    //@Transactional(Transactional.TxType.SUPPORTS)
    //@Transactional(Transactional.TxType.MANDATORY)
    public Student updateStudent(Student updatedStudent, Integer studentId) {
        Student studentToUpdate = studentRepository.findById(studentId).get();
        studentToUpdate.setStudentAddress(updatedStudent.getStudentAddress());
        studentToUpdate.setStudentMarks(updatedStudent.getStudentMarks());
        studentToUpdate.setStudentName(updatedStudent.getStudentName());
        return studentRepository.save(studentToUpdate);
    }
}
