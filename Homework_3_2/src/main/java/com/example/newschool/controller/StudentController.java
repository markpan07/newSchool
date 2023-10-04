package com.example.newschool.controller;

import com.example.newschool.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.newschool.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable long id) {
        return studentService.get(id);
    }

    @DeleteMapping("/{id}")
    public Student update(@PathVariable long id) {
        return studentService.delete(id);
    }

    @GetMapping("/findByAge")
    public List<Student> findByAge(@RequestParam Integer age) {
        return studentService.findByAge(age);
    }

    @GetMapping("/all")
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

}
