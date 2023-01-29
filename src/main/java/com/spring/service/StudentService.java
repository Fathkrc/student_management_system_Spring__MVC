package com.spring.service;

import com.spring.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student findById(Long id);

    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Long id);
}
