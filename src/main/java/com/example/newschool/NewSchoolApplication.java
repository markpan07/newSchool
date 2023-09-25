package com.example.newschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class NewSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewSchoolApplication.class, args);
    }

}
