package com.spring.repository;


import com.spring.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    List<Student> getAll();

    Optional<Student> findById(Long id);

    void save(Student student);

    void update(Student student);

    void delete(Long id);


}
