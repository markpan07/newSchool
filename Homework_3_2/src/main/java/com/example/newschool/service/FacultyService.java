package com.example.newschool.service;

import com.example.newschool.exception.FacultyListIsEmptyException;
import com.example.newschool.exception.FacultyNotFoundException;
import com.example.newschool.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private HashMap<Long, Faculty> faculties = new HashMap<>();
    private long count = 0;

    public Faculty create(Faculty faculty) {
        faculty.setId(++count);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty update(long id, Faculty faculty) {
        if (faculties.containsKey(id)) {
            Faculty oldFaculty = faculties.get(id);
            oldFaculty.setColor(faculty.getColor());
            oldFaculty.setName(faculty.getName());
            faculties.replace(id, oldFaculty);
            return oldFaculty;
        } else {
            throw new FacultyNotFoundException(id);
        }
    }

    public Faculty delete(long id) {
        if (faculties.containsKey(id)) {
            return faculties.remove(id);
        } else {
            throw new FacultyNotFoundException(id);
        }
    }

    public Faculty get(long id) {
        if (faculties.containsKey(id)) {
            return faculties.get(id);
        } else {
            throw new FacultyNotFoundException(id);
        }
    }

    public List<Faculty> findByColor(String color) {
        List<Faculty> result = faculties.values().stream()
                .filter(c -> c.getColor().equals(color))
                .collect(Collectors.toList());
        return result;
    }

    public List<Faculty> getAllFaculties(){
        List<Faculty> collect = new ArrayList<>(faculties.values());
        if(collect.isEmpty()) {
            throw new FacultyListIsEmptyException();
        }
        return collect;
    }

    public void clear(){
        faculties.clear();
        count = 0;
    }

}
