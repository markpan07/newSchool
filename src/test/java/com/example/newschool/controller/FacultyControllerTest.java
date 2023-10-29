package com.example.newschool.controller;

import com.example.newschool.DTO.FacultyDTO;
import com.example.newschool.repository.FacultyRepository;
import org.apache.coyote.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    FacultyDTO fac1 = new FacultyDTO("faculty name 1", "faculty color 1");


    FacultyDTO fac2 = new FacultyDTO("faculty name 2", "faculty color 2");
    FacultyDTO fac3 = new FacultyDTO("faculty name 3", "faculty color 3");

    @BeforeEach
    public void beforeEach() {
        facultyRepository.deleteAll();

    }

    @Test
    public void createFacultyTest() {

        FacultyDTO response = restTemplate.postForObject("/faculties", fac1, FacultyDTO.class);
        Assertions.assertThat(response.getColor()).isEqualTo("faculty color 1");
        Assertions.assertThat(response.getId()).isEqualTo(1L);
        Assertions.assertThat(response.getName()).isEqualTo("faculty name 1");

        FacultyDTO response2 = restTemplate.postForObject("/faculties", fac2, FacultyDTO.class);
        Assertions.assertThat(response2.getColor()).isEqualTo("faculty color 2");
        Assertions.assertThat(response2.getId()).isEqualTo(2L);
        Assertions.assertThat(response2.getName()).isEqualTo("faculty name 2");
    }

    @Test
    public void getFacultyTest() {
        FacultyDTO expected = new FacultyDTO("faculty name 2", "faculty color 2");
        expected.setId(2L);

        FacultyDTO response = restTemplate.postForObject("/faculties", fac1, FacultyDTO.class);
        FacultyDTO response2 = restTemplate.postForObject("/faculties", fac2, FacultyDTO.class);

        Assertions.assertThat(response2).isEqualTo(expected);

    }

    @Test
    public void updateFacultyTest() {
        FacultyDTO newFac = new FacultyDTO("new name", "new color");
        newFac.setId(1L);
        FacultyDTO firstFac = restTemplate.postForObject("/faculties/", fac1, FacultyDTO.class);
        Assertions.assertThat(firstFac.getId()).isEqualTo(1L);

        ResponseEntity<FacultyDTO> response = restTemplate.exchange(
                "/faculties/" + firstFac.getId(),
                HttpMethod.PUT,
                new HttpEntity<>(newFac),
                FacultyDTO.class
        );

        Assertions.assertThat(response.getBody().getName()).isEqualTo("new name");
        Assertions.assertThat(response.getBody().getId()).isEqualTo(1L);
        Assertions.assertThat(response.getBody().getColor()).isEqualTo(newFac.getColor());




    }

    @Test
    public void deleteFacultyTest() {

    }

}
