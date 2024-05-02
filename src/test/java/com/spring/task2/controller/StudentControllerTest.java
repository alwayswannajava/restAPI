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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

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
    void testReadStudentById() throws Exception {
        long studentId = 3L;
        ResultActions result = mockMvc.perform(get("/api/students/{id}", studentId));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.studentId").value(studentId))
                .andExpect(jsonPath("$.firstName").value("Mykhailo"))
                .andExpect(jsonPath("$.lastName").value("Golybev"))
                .andExpect(jsonPath("$.groupName").value("AK-92"));
    }

    @Test
    void testCorrectUpdateStudent() throws Exception {
        long studentId = 3L;
        String updatedStudentJson = "{\"groupName\":\"AK-92\",\"firstName\":\"Mykhailo\",\"lastName\":\"Golybev\"}";
        ResultActions result = mockMvc.perform(put("/api/students/{id}", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedStudentJson));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.groupName").value("AK-92"))
                .andExpect(jsonPath("$.firstName").value("Mykhailo"))
                .andExpect(jsonPath("$.lastName").value("Golybev"));
    }

    @Test
    void testCorrectDeleteStudent() throws Exception {
        long studentId = 3L;
        ResultActions result = mockMvc.perform(delete("/api/students/{id}", studentId));
        result.andExpect(status().isOk());
    }

    @Test
    void testCorrectStudentsByPage() throws Exception {
        String studentJson = "{\n" +
                "    \"pageNumber\" : 0,\n" +
                "    \"pageSize\" : 10,\n" +
                "    \"key\" : \"firstName\",\n" +
                "    \"operation\" : \":\",\n" +
                "    \"value\" : \"Mykhailo\"\n" +
                "}";

        ResultActions result = mockMvc.perform(post("/api/students/_list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJson));
        result.andExpect(status().isOk());
    }

    @Test
    void testCorrectGenerateReport() throws Exception {
        String studentJson = "{\n" +
                "    \"key\" : \"firstName\",\n" +
                "    \"operation\" : \":\",\n" +
                "    \"value\" : \"Mykhailo\"\n" +
                "}";

        ResultActions result = mockMvc.perform(post("/api/students/_report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJson));
        result.andExpect(status().isOk());
    }

    @Test
    void testCorrectUploadFromFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "students.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/api/students/upload").file(file))
                .andExpect(status().isOk());
    }
}