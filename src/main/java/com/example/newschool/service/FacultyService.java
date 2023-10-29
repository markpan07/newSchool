package com.example.newschool.service;

import com.example.newschool.DTO.FacultyDTO;
import com.example.newschool.Mapper.FacultyMapper;
import com.example.newschool.exception.FacultyListIsEmptyException;
import com.example.newschool.exception.FacultyNotFoundException;
import com.example.newschool.model.Faculty;
import com.example.newschool.model.Student;
import com.example.newschool.repository.FacultyRepository;
import com.example.newschool.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    public final FacultyMapper mapper;
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;


    public FacultyService(FacultyMapper mapper, FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.mapper = mapper;
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public FacultyDTO create(FacultyDTO dto) {
        return mapper.toDTO(facultyRepository.save(mapper.toEntity(dto)));

    }


    public FacultyDTO update(Long id, FacultyDTO dto) {
        if (facultyRepository.existsById(id)) {
            dto.setId(id);
            return mapper.toDTO(facultyRepository.save(mapper.toEntity(dto)));
        } else {
            throw new FacultyNotFoundException(id);
        }
    }

    public FacultyDTO delete(long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
        facultyRepository.delete(faculty);
        return mapper.toDTO(faculty);
    }

    public FacultyDTO get(long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
        return mapper.toDTO(faculty);
    }

    public List<FacultyDTO> findByColor(String color) {
        List<Faculty> faculties = facultyRepository.findAllByColor(color);
        if (faculties.isEmpty()) {
            throw new FacultyListIsEmptyException();
        }
        return faculties.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<FacultyDTO> getAllFaculties() {
        List<Faculty> collect = facultyRepository.findAll().stream().collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new FacultyListIsEmptyException();
        }
        return collect.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<FacultyDTO> findAllByColorOrName(String colorOrName) {
        List<Faculty> collect = facultyRepository.findAllByColorContainingIgnoreCaseOrNameContainingIgnoreCase(colorOrName, colorOrName);
        return collect.stream().map(mapper::toDTO).collect(Collectors.toList());
    }


    //Fix it later for DTO
    public List<Student> findByFacultyId(Long id) {
        return studentRepository.findByFacultyId(id);
    }

    public void clear() {
        facultyRepository.deleteAll();
    }

}
