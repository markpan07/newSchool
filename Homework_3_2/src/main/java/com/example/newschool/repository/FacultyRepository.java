package com.example.newschool.repository;

import com.example.newschool.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    public List<Faculty> findAllByColor(String color);
    public List<Faculty> findAllByColorContainingIgnoreCaseOrNameContainingIgnoreCase(String color, String name);
}
