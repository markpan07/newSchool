package com.example.newschool.service;

import com.example.newschool.DTO.FacultyDTO;
import com.example.newschool.DTO.StudentDTO;
import com.example.newschool.Mapper.FacultyMapper;
import com.example.newschool.Mapper.StudentMapper;
import com.example.newschool.exception.StudentListIsEmptyException;
import com.example.newschool.exception.StudentNotFoundException;
import com.example.newschool.model.Student;
import com.example.newschool.repository.FacultyRepository;
import com.example.newschool.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final StudentMapper studentMapper;
    private final FacultyMapper facultyMapper;


    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository, StudentMapper studentMapper, FacultyMapper facultyMapper) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.studentMapper = studentMapper;
        this.facultyMapper = facultyMapper;
    }

    public StudentDTO create(StudentDTO dto) {
        dto.setId(null);
        Student student = studentMapper.toEntity(dto);
        student.setFaculty(facultyRepository.findById(dto.getFaculty_id()).orElse(null));
        return studentMapper.toDTO(studentRepository.save(student));
    }

    public StudentDTO update(long id, StudentDTO dto) {
        if (studentRepository.existsById(id)) {
            dto.setId(id);
            return studentMapper.toDTO(studentRepository.save(studentMapper.toEntity(dto)));
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public StudentDTO delete(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
        return studentMapper.toDTO(student);
    }

    public StudentDTO get(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return studentMapper.toDTO(student);
    }

    public List<StudentDTO> findAllByAge(Integer age) {
        List<Student> faculties = studentRepository.findAllByAge(age);
        if (faculties.isEmpty()) {
            throw new StudentListIsEmptyException();
        }
        return faculties.stream().map(studentMapper::toDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> collect = studentRepository.findAll().stream().collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new StudentListIsEmptyException();
        }
        return collect.stream().map(studentMapper::toDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> findByAgeBetween(Integer min, Integer max) {
        return studentRepository.findAllByAgeBetween(min, max).stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FacultyDTO getFacultyByStudentId(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        return facultyMapper.toDTO(student.getFaculty());
    }

    public void clear() {
        studentRepository.deleteAll();
    }

}
