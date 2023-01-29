package com.spring.service;

import com.spring.domain.Student;
import com.spring.exception.ResourceNotFound;
import com.spring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSrvImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> getAllStudents() {

        return studentRepository.getAll();
    }

    @Override
    public Student findById(Long id) {

     Student student=studentRepository.findById(id).orElseThrow(
             ()->new ResourceNotFound(id+" id'li Student bulunamadı"));
     return student;
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);

    }

    @Override
    public void updateStudent(Student student) {
    studentRepository.update(student);
    }

    @Override
    public void deleteStudent(Long id) {
    studentRepository.delete(id);
    }
}
