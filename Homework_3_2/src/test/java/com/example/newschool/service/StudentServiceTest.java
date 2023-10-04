package com.example.newschool.service;

import com.example.newschool.exception.StudentListIsEmptyException;
import com.example.newschool.exception.StudentNotFoundException;
import com.example.newschool.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.newschool.constants.StudentServiceTestConstants.*;
import static com.example.newschool.constants.StudentServiceTestConstants.STUDENTS_COLLECTION_AFTER_REMOVE_FIRST;

class StudentServiceTest {

    private static final StudentService out = new StudentService();

    @BeforeEach
    public void setUp() {
        out.create(new Student(13L, "student1", 21));
        out.create(new Student(13L, "student2", 21));
        out.create(new Student(13L, "student3", 23));
        out.create(new Student(13L, "student4", 24));
    }

    @AfterEach
    public void after() {
        out.clear();
    }

    @Test
    void shouldReturnStudentWhenIdIsCorrect() {
        Assertions.assertThat(out.get(1)).isEqualTo(STUDENT_1);
    }

    @Test
    void shouldReturnExceptionWhenIncorrectId() {
        Assertions.assertThatExceptionOfType(StudentNotFoundException.class).
                isThrownBy(() -> out.get(100));
        Assertions.assertThatExceptionOfType(StudentNotFoundException.class).
                isThrownBy(() -> out.update(100, EDITED_STUDENT));
        Assertions.assertThatExceptionOfType(StudentNotFoundException.class).
                isThrownBy(() -> out.delete(100));
    }

    @Test
    void shouldReturnExceptionWhenListIsEmpty(){
        out.clear();
        Assertions.assertThatExceptionOfType(StudentListIsEmptyException.class).
                isThrownBy(() -> out.getAllStudents());
    }

    @Test
    void shouldReturnEditedStudentIfIdIsCorrect() {
        Assertions.assertThat(out.update(4, new Student(1L, "editedName", 30))).isEqualTo(EDITED_STUDENT);
    }


    @Test
    void shouldReturnAGE_21_COLLECTION() {
        Assertions.assertThat(out.findByAge(21)).isEqualTo(AGE_21_COLLECTION);
    }

    @Test
    void shouldReturnAllStudentsInCollection(){
        Assertions.assertThat(out.getAllStudents()).isEqualTo(STUDENTS_COLLECTION);
    }


    @Test
    void shouldReturnCollectionAfterRemoveFirst() {
        Assertions.assertThat(out.delete(1)).isEqualTo(STUDENT_1);
        Assertions.assertThat(out.getAllStudents()).isEqualTo(STUDENTS_COLLECTION_AFTER_REMOVE_FIRST);
    }

}