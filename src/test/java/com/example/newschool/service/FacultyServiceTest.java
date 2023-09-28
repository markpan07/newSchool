package com.example.newschool.service;

import com.example.newschool.model.Faculty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FacultyServiceTest {

    private static final FacultyService out = new FacultyService();

    @BeforeAll
    static void setUp() {
        Faculty st1 = out.create(new Faculty(15L, "name", "red"));
        Faculty st2 = out.create(new Faculty(15L, "name2", "red"));
        Faculty st3 = out.create(new Faculty(15L, "name3", "yellow"));
        Faculty st4 = out.create(new Faculty(15L, "name4", "red"));
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
    void findByColor() {
        List<Faculty> expected1 = new ArrayList<>();
        expected1.add(new Faculty(1L, "name", "red"));
        expected1.add(new Faculty(2L, "name2", "red"));
        expected1.add(new Faculty(4L, "name4", "red"));

        List<Faculty> expected2 = new ArrayList<>();
        expected2.add(new Faculty(3L, "name3", "yellow"));

        Assertions.assertThat(out.findByColor("red")).isEqualTo(expected1);
        Assertions.assertThat(out.findByColor("yellow")).isEqualTo(expected2);



    }
}