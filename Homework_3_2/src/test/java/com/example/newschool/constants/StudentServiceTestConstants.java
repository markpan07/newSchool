package com.example.newschool.constants;

import com.example.newschool.model.Faculty;
import com.example.newschool.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceTestConstants {
    public static final Student STUDENT_1 = new Student(1L, "student1", 21);
    public static final Student STUDENT_2 = new Student(2L, "student2", 21);
    public static final Student STUDENT_3 = new Student(3L, "student3", 23);
    public static final Student STUDENT_4 = new Student(4L, "student4", 24);
    public static final Student EDITED_STUDENT = new Student(4L, "editedName", 30);

    public static final List<Student> STUDENTS_COLLECTION = new ArrayList<>(List.of(
            STUDENT_1,
            STUDENT_2,
            STUDENT_3,
            STUDENT_4
    ));

    public static final List<Student> AGE_21_COLLECTION = new ArrayList<>(List.of(
            STUDENT_1,
            STUDENT_2
    ));
    public static final Long ID = 4L;

    public static final List<Student> STUDENTS_COLLECTION_AFTER_REMOVE_FIRST = new ArrayList<>(List.of(
            STUDENT_2,
            STUDENT_3,
            STUDENT_4
    ));

    public static final List<Student> STUDENTS_EDITED_COLLECTION = new ArrayList<>(List.of(
            STUDENT_1,
            STUDENT_2,
            STUDENT_3,
            EDITED_STUDENT
    ));
}
