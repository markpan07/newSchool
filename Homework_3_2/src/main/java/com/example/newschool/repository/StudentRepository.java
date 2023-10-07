package com.example.newschool.repository;

import com.example.newschool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findAllByAge(Integer age);
}
