package com.spring.task2.controller;

import com.spring.task2.service.GroupService;
import com.spring.task2.service.StudentService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;

    @Test
    void testCorrectCreateStudent() throws Exception {
        String studentJson = "{\n" +
                "    \"groupName\": \"WA-21\",\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Doe\"\n" +
                "  }";

        ResultActions result = mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJson));

        result.andExpect(status().isOk());
    }

    @Test
    void testReadStudentById() {

    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void getStudentsByPage() {
    }

    @Test
    void generateReport() {
    }

    @Test
    void uploadFromFile() {
    }
}