package com.example.newschool.constants;

import com.example.newschool.model.Faculty;

import java.util.ArrayList;
import java.util.List;

public class FacultyServiceTestConstants {
    public static final Faculty FACULTY_1 = new Faculty(1L, "faculty1", "red");
    public static final Faculty FACULTY_2 = new Faculty(2L, "faculty2", "red");
    public static final Faculty FACULTY_3 = new Faculty(3L, "faculty3", "yellow");
    public static final Faculty FACULTY_4 = new Faculty(4L, "faculty4", "green");
    public static final Faculty EDITED_FACULTY = new Faculty(4L, "editedName", "editedColor");

    public static final List<Faculty> FACULTIES_COLLECTION = new ArrayList<>(List.of(
            FACULTY_1,
            FACULTY_2,
            FACULTY_3,
            FACULTY_4
    ));

    public static final List<Faculty> RED_FACULTIES_COLLECTION = new ArrayList<>(List.of(
            FACULTY_1,
            FACULTY_2
    ));
    public static final Long ID = 4L;

    public static final List<Faculty> FACULTIES_COLLECTION_AFTER_REMOVE_FIRST = new ArrayList<>(List.of(
            FACULTY_2,
            FACULTY_3,
            FACULTY_4
    ));

    public static final List<Faculty> FACULTIES_EDITED_COLLECTION = new ArrayList<>(List.of(
            FACULTY_1,
            FACULTY_2,
            FACULTY_3,
            EDITED_FACULTY
    ));
}
