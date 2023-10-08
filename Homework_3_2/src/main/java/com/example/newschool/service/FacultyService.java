package com.example.newschool.service;

import com.example.newschool.exception.FacultyListIsEmptyException;
import com.example.newschool.exception.FacultyNotFoundException;
import com.example.newschool.model.Faculty;
import com.example.newschool.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty create(Faculty faculty) {
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    public Faculty update(long id, Faculty faculty) {
        if (facultyRepository.existsById(id)) {
            faculty.setId(id);
            return facultyRepository.save(faculty);
        } else {
            throw new FacultyNotFoundException(id);
        }
    }

    public Faculty delete(long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
        facultyRepository.delete(faculty);
        return faculty;
    }

    public Faculty get(long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
        return faculty;
    }

    public List<Faculty> findByColor(String color) {
        List<Faculty> faculties = facultyRepository.findAllByColor(color);
        if (faculties.isEmpty()) {
            throw new FacultyListIsEmptyException();
        }
        return faculties;
    }

    public List<Faculty> getAllFaculties() {
        List<Faculty> collect = facultyRepository.findAll().stream().collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new FacultyListIsEmptyException();
        }
        return collect;
    }

    public List<Faculty> findAllByColorOrName(String colorOrName) {
        return facultyRepository.findAllByColorContainingIgnoreCaseOrNameContainingIgnoreCase(colorOrName, colorOrName);
    }

    public void clear() {
        facultyRepository.deleteAll();
    }

}
