package com.example.newschool.controller;

import com.example.newschool.DTO.FacultyDTO;
import com.example.newschool.DTO.StudentDTO;
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
    public StudentDTO create(@RequestBody StudentDTO student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public StudentDTO update(@PathVariable long id, @RequestBody StudentDTO student) {
        return studentService.update(id, student);
    }

    @GetMapping("/{id}")
    public StudentDTO get(@PathVariable long id) {
        return studentService.get(id);
    }

    @DeleteMapping("/{id}")
    public StudentDTO delete(@PathVariable long id) {
        return studentService.delete(id);
    }


    @GetMapping("/all")
    public List<StudentDTO> getAll(@RequestParam (required = false) Integer age) {
        if(age != null) {
           return studentService.findAllByAge(age);
        }
        return studentService.getAllStudents();
    }

    @GetMapping("/findAllByAgeBetween")
    public List<StudentDTO> findByAgeBetween(@RequestParam Integer min, @RequestParam Integer max){
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping("{id}/faculty)")
    public FacultyDTO getFacultyByStudentId(@PathVariable Long id) {
        return studentService.getFacultyByStudentId(id);
    }


}
