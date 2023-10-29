package com.example.newschool.Mapper;

import com.example.newschool.DTO.FacultyDTO;
import com.example.newschool.model.Faculty;
import org.springframework.stereotype.Service;

@Service
public class FacultyMapper {

    public FacultyDTO toDTO(Faculty faculty) {
        FacultyDTO dto = new FacultyDTO();
        dto.setId(faculty.getId());
        dto.setName(faculty.getName());
        dto.setColor(faculty.getColor());
        return dto;
    }

    public Faculty toEntity(FacultyDTO dto) {
        Faculty faculty = new Faculty();
        faculty.setName(dto.getName());
        faculty.setId(dto.getId());
        faculty.setColor(dto.getColor());
        return faculty;

    }


}
