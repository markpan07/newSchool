package com.example.newschool.Mapper;

import com.example.newschool.DTO.StudentDTO;
import com.example.newschool.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toEntity(StudentDTO dto) {
        Student entity = new Student();
        entity.setId(dto.getId());
        entity.setAge(dto.getAge());
        entity.setName(dto.getName());

        return entity;
    }

    public StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setAge(student.getAge());
        dto.setName(student.getName());
        dto.setId(student.getId());
        dto.setFaculty_id(student.getFaculty().getId());
        return dto;
    }

}
