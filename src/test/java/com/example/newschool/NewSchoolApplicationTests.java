package com.example.newschool;

import com.example.newschool.DTO.FacultyDTO;
import com.example.newschool.constants.*;
import com.example.newschool.controller.FacultyController;
import com.example.newschool.controller.StudentController;
import com.example.newschool.model.Faculty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NewSchoolApplicationTests {
 /*
    @LocalServerPort
    private int port;
    @Autowired
    private FacultyController facultyController;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    ObjectMapper om = new ObjectMapper();

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void getFacultyTest() throws Exception {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculties/all", String.class))
                .isNotNull();
    }

    @Test
    void createFacultyTest() throws Exception {
        Faculty faculty1 = new Faculty();
        faculty1.setName("faculty1");
        faculty1.setColor("red");
        FacultyDTO facultyDTO1 = new FacultyDTO();
        facultyDTO1.setId(1L);
        facultyDTO1.setName("faculty1");
        facultyDTO1.setColor("red");
        Assertions.assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/faculties/",
                        faculty1,
                        String.class))
                .isEqualTo(om.writeValueAsString(facultyDTO1));
    }

    @Test
    void updateFacultyTest() throws Exception {
        Faculty newFaculty = new Faculty();
        newFaculty.setName("newFaculty");
        newFaculty.setColor("new color");
        FacultyDTO facultyDTO1 = new FacultyDTO();
        facultyDTO1.setId(1L);
        facultyDTO1.setName("newFaculty");
        facultyDTO1.setColor("new color");
        Assertions.assertThat(this.testRestTemplate.put("http://localhost:" + port + "/faculties/{1}/",
                        newFaculty, ))
                .isEqualTo(om.writeValueAsString(facultyDTO1));
    }

    @Test
    void deleteFacultyTest() throws Exception {

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");

        Faculty newFaculty = new Faculty();
        newFaculty.setName("newFaculty");
        newFaculty.setColor("new color");
        FacultyDTO facultyDTO1 = new FacultyDTO();
        facultyDTO1.setId(1L);
        facultyDTO1.setName("newFaculty");
        facultyDTO1.setColor("new color");
        Assertions.assertThat(this.testRestTemplate.delete("http://localhost:" + port + "/faculties/{id}", params);
    }
*/
}
