package com.example.newschool.controller;

import com.example.newschool.model.Faculty;
import com.example.newschool.model.Student;
import org.springframework.web.bind.annotation.*;
import com.example.newschool.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return facultyService.create(faculty);
    }

    @GetMapping("/{id}")
    public Faculty get(@PathVariable long id) {
        return facultyService.get(id);
    }

    @PutMapping("/{id}")
    public Faculty update(@PathVariable long id, @RequestBody Faculty faculty) {
        return facultyService.update(id, faculty);
    }

    @DeleteMapping("/{id}")
    public Faculty delete(@PathVariable long id) {
        return facultyService.delete(id);
    }

    @GetMapping("/findByColor")
    public List<Faculty> findByColor(@RequestParam String color) {
        return facultyService.findByColor(color);
    }

    @GetMapping("/all")
    public List<Faculty> getAll(@RequestParam(required = false) String color) {
        if (color != null) {
            return facultyService.findByColor(color);
        }
        return facultyService.getAllFaculties();
    }

    @GetMapping("/findAllByColorOrName")
    public List<Faculty> findAllByColorOrName(@RequestParam String colorOrName) {
        return facultyService.findAllByColorOrName(colorOrName);
    }

    @GetMapping("{id}/students")
    public List<Student> findByFacultyId(@PathVariable Long id) {
        return facultyService.findByFacultyId(id);
    }
}
