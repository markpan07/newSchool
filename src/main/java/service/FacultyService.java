package service;

import model.Faculty;
import model.Student;
import org.springframework.stereotype.Service;
import exception.FacultyNotFoundException;

import java.util.HashMap;

@Service
public class FacultyService {
    private HashMap<Long, Faculty> faculties = new HashMap<>();
    private long count = 0;

    public Faculty create(Faculty faculty) {
        faculty.setId(++count);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty update(long id, Faculty faculty){
        if(faculties.containsKey(id)) {
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
        if(faculties.containsKey(id)) {
            return faculties.remove(id);
        } else {
            throw new FacultyNotFoundException(id);
        }
    }

    public Faculty get(long id) {
        if(faculties.containsKey(id)) {
            return faculties.get(id);
        } else {
            throw new FacultyNotFoundException(id);
        }
    }

}
