package com.example.newschool.service;

import com.example.newschool.exception.StudentListIsEmptyException;
import com.example.newschool.exception.StudentNotFoundException;
import com.example.newschool.model.Student;
import com.example.newschool.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student create(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student update(long id, Student student) {
        if (studentRepository.existsById(id)) {
            return studentRepository.save(student);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public Student delete(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
        return student;
    }

    public Student get(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return student;
    }

    public List<Student> findAllByAge(Integer age) {
        List <Student> faculties = studentRepository.findAllByAge(age);
        if (faculties.isEmpty()){
            throw new StudentListIsEmptyException();
        }
        return faculties;
    }

    public List<Student> getAllStudents(){
        List<Student> collect = studentRepository.findAll().stream().collect(Collectors.toList());
        if(collect.isEmpty()) {
            throw new StudentListIsEmptyException();
        }
        return collect;
    }

    public void clear(){
        studentRepository.deleteAll();
    }

}
