package com.example.newschool.service;

import static com.example.newschool.constants.FacultyServiceTestConstants.*;

import com.example.newschool.exception.FacultyListIsEmptyException;
import com.example.newschool.exception.FacultyNotFoundException;
import com.example.newschool.exception.StudentListIsEmptyException;
import com.example.newschool.model.Faculty;
import com.example.newschool.repository.FacultyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FacultyServiceTest {

    private static final FacultyService out = new FacultyService();


    @BeforeEach
    public void setUp() {
        out.create(new Faculty(13L, "faculty1", "red"));
        out.create(new Faculty(13L, "faculty2", "red"));
        out.create(new Faculty(13L, "faculty3", "yellow"));
        out.create(new Faculty(13L, "faculty4", "green"));
    }

    @AfterEach
    public void after() {
        out.clear();
    }

    @Test
    void shouldReturnFacultyWhenIdIsCorrect() {
        Assertions.assertThat(out.get(1)).isEqualTo(FACULTY_1);
    }

    @Test
    void shouldReturnExceptionWhenIncorrectId() {
        out.clear();
        Assertions.assertThatExceptionOfType(FacultyNotFoundException.class).
                isThrownBy(() -> out.get(100));
        Assertions.assertThatExceptionOfType(FacultyNotFoundException.class).
                isThrownBy(() -> out.update(100, EDITED_FACULTY));
        Assertions.assertThatExceptionOfType(FacultyNotFoundException.class).
                isThrownBy(() -> out.delete(100));
        Assertions.assertThatExceptionOfType(FacultyListIsEmptyException.class).
                isThrownBy(() -> out.getAllFaculties());
    }

    @Test
    void shouldReturnExceptionWhenListIsEmpty(){
        out.clear();
        Assertions.assertThatExceptionOfType(FacultyListIsEmptyException.class).
                isThrownBy(() -> out.getAllFaculties());
    }

    @Test
    void shouldReturnEditedFacultyIfIdIsCorrect() {
        Assertions.assertThat(out.update(4, new Faculty(1L, "editedName", "editedColor"))).isEqualTo(EDITED_FACULTY);
    }


    @Test
    void shouldReturnRED_FACULTIES_COLLECTION() {
        Assertions.assertThat(out.findByColor("red")).isEqualTo(RED_FACULTIES_COLLECTION);
    }

    @Test
    void shouldReturnCollectionOfAllFaculties() {
        Assertions.assertThat(out.getAllFaculties()).isEqualTo(FACULTIES_COLLECTION);
    }

    @Test
    void shouldReturnFacultiesCollectionAfterRemoveFirst() {
        Assertions.assertThat(out.delete(1)).isEqualTo(FACULTY_1);
        Assertions.assertThat(out.getAllFaculties()).isEqualTo(FACULTIES_COLLECTION_AFTER_REMOVE_FIRST);
    }


}