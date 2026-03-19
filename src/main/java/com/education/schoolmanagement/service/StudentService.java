package com.education.schoolmanagement.service;

import com.education.schoolmanagement.Model.Student;
import com.education.schoolmanagement.Util.CacheInspectUtil;
import com.education.schoolmanagement.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    StudentRepository studentRepository;
    Logger log = LoggerFactory.getLogger(StudentService.class);
    CacheInspectUtil cacheInspectUtil;

    public StudentService(StudentRepository studentRepository,
                          CacheInspectUtil cacheInspectUtil) {
        this.studentRepository = studentRepository;
        this.cacheInspectUtil = cacheInspectUtil;
    }

    public Student insertStudent(Student student){
        return studentRepository.save(student);
    }

    @Cacheable(value = "Students")
    public Map<Integer,Student> getAllStudents(){
        log.info("inside controller");
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
    @CachePut(value = "Students",key = "#studentId")
    public Student updateStudent(Student updatedStudent, Integer studentId) {
        Student studentToUpdate = studentRepository.findById(studentId).get();
        studentToUpdate.setStudentAddress(updatedStudent.getStudentAddress());
        studentToUpdate.setStudentMarks(updatedStudent.getStudentMarks());
        studentToUpdate.setStudentName(updatedStudent.getStudentName());
        return studentRepository.save(studentToUpdate);
    }
}
