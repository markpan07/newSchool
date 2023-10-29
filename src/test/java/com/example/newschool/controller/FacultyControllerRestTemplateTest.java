package com.example.newschool.controller;

import com.example.newschool.DTO.FacultyDTO;
import com.example.newschool.repository.FacultyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerRestTemplateTest {
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
    FacultyDTO fac4 = new FacultyDTO("faculty name 4", "faculty color 4");

    @BeforeAll
    public void beforeAll() {
        facultyRepository.deleteAll();
        FacultyDTO dto1 = restTemplate.postForObject("/faculties", fac1, FacultyDTO.class);
        FacultyDTO dto2 = restTemplate.postForObject("/faculties", fac2, FacultyDTO.class);
        FacultyDTO dto3 = restTemplate.postForObject("/faculties", fac3, FacultyDTO.class);

    }

    @Test
    public void createFacultyTest() {

        FacultyDTO dto4 = restTemplate.postForObject("/faculties", fac4, FacultyDTO.class);
        Assertions.assertThat(dto4.getColor()).isEqualTo("faculty color 4");
        Assertions.assertThat(dto4.getId()).isEqualTo(4L);
        Assertions.assertThat(dto4.getName()).isEqualTo("faculty name 4");


    }

    @Test
    public void getFacultyTest() {
        FacultyDTO expected = new FacultyDTO("faculty name 2", "faculty color 2");
        expected.setId(2L);

        FacultyDTO response = restTemplate.getForObject("/faculties/2", FacultyDTO.class);

        Assertions.assertThat(response).isEqualTo(expected);

    }

    @Test
    public void updateFacultyTest() {
        FacultyDTO newFac = new FacultyDTO("new name", "new color");


        ResponseEntity<FacultyDTO> response = restTemplate.exchange(
                "/faculties/1",
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

        FacultyDTO temp = fac2;
                ResponseEntity<FacultyDTO> response = restTemplate.exchange(
                "/faculties/2" ,
                HttpMethod.DELETE,
                null,
                FacultyDTO.class
        );
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        temp.setId(2L);
        Assertions.assertThat(response.getBody()).isEqualTo(temp);
        temp.setId(0L);



        //Проверка не проходит. Почему?
        //Assertions.assertThatThrownBy(() -> facultyController.get(1)).isEqualTo(new FacultyNotFoundException(1));

        //Также не проходит
        //Assertions.assertThatThrownBy(()-> restTemplate.getForObject("/faculties/" + firstFac.getId(), FacultyDTO.class))
         //       .isEqualTo(new FacultyNotFoundException(1));


    }

}
