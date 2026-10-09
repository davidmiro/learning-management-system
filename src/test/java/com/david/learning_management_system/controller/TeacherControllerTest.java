package com.david.learning_management_system.controller;

import com.david.learning_management_system.dto.request.TeacherCreateDto;
import com.david.learning_management_system.dto.request.TeacherUpdateDto;
import com.david.learning_management_system.dto.response.TeacherResponseDto;
import com.david.learning_management_system.model.Teacher;
import com.david.learning_management_system.repository.TeacherRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class TeacherControllerTest {

    @Autowired
    private TeacherRepository teacherRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @LocalServerPort
    private int port;

    private String collectionUrl;
    private Long savedTeacherId;

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @BeforeEach
    void helper() {

        collectionUrl = "http://localhost:" + port + "/lms/teachers/v1";

        Teacher teacher = new Teacher();
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        savedTeacherId = teacherRepository.save(teacher).getId();
    }

    @AfterEach
    void tearDown() {
        teacherRepository.deleteAll();
    }

    @DynamicPropertySource
    static void datasourceProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.liquibase.enabled", () -> "false");
    }


    @Test
    void getTeacherById_shouldReturnDto() {

        String url = collectionUrl + "/" + savedTeacherId;

        ResponseEntity<TeacherResponseDto> response =
                restTemplate.getForEntity(url, TeacherResponseDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isEqualTo(savedTeacherId);
        assertThat(response.getBody().firstName()).isEqualTo("John");
        assertThat(response.getBody().lastName()).isEqualTo("Doe");
    }

    @Test
    void createTeacher_shouldReturnCreated() {

        TeacherCreateDto dto = new TeacherCreateDto("Jane", "Smith");

        ResponseEntity<TeacherResponseDto> response =
                restTemplate.postForEntity(collectionUrl + "/", dto, TeacherResponseDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isNotNull();
        assertThat(response.getBody().firstName()).isEqualTo("Jane");
        assertThat(teacherRepository.findById(response.getBody().id())).isPresent();
    }

    @Test
    void updateTeacher_shouldUpdateFields() {

        TeacherUpdateDto dto = new TeacherUpdateDto("Johnny", "Doe");
        HttpEntity<TeacherUpdateDto> entity = new HttpEntity<>(dto);

        String url = collectionUrl + "/" + savedTeacherId;

        ResponseEntity<TeacherResponseDto> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                TeacherResponseDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().firstName()).isEqualTo("Johnny");
    }

    @Test
    void deleteTeacher_shouldReturnNoContent() {

        String url = collectionUrl + "/" + savedTeacherId;

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(teacherRepository.findById(savedTeacherId)).isEmpty();
    }

    @Test
    void getTeacherById_whenNotFound_shouldReturn404() {

        String url = collectionUrl + "/999999";

        try {
            restTemplate.getForEntity(url, String.class);
            assertThat(false).as("ожидали 404").isTrue();
        } catch (HttpClientErrorException.NotFound ex) {
            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}