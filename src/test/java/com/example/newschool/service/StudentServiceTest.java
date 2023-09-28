package com.example.newschool.service;

import com.example.newschool.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private static final StudentService out = new StudentService();

    @BeforeAll
    static void setUp() {
        Student st1 = out.create(new Student(15L, "name", 22));
        Student st2 = out.create(new Student(15L, "name2", 20));
        Student st3 = out.create(new Student(15L, "name3", 27));
        Student st4 = out.create(new Student(15L, "name3", 27));
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void findByAge() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1L, "name", 22));

        List<Student> list2 = new ArrayList<>();
        list2.add(new Student(3L, "name3", 27));
        list2.add(new Student(4L, "name3", 27));
        Assertions.assertThat(out.findByAge(22))
                .isEqualTo(list);
        Assertions.assertThat(out.findByAge(27))
                .isEqualTo(list2);
    }
}