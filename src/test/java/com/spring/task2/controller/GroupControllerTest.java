package com.spring.task2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCorrectFindAll() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/groups"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void testCorrectCreateGroup() throws Exception {
        String groupJson = "{\n" +
                "    \"groupId\": \"11\",\n" +
                "    \"groupName\": \"WO-21\",\n" +
                "  }";

        ResultActions result = mockMvc.perform(post("/api/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(groupJson));

        result.andExpect(status().isOk());
    }

    @Test
    void testCorrectUpdateGroup() throws Exception {
        long groupId = 3L;
        String updatedGroup = "{\"groupId\":\"3\",\"groupName\":\"LT-91\"}";
        ResultActions result = mockMvc.perform(put("/api/groups/{groupId}", groupId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedGroup));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.groupName").value("LT-91"));
    }

    @Test
    void testCorrectDeleteGroup() throws Exception {
        long groupId = 3L;
        ResultActions result = mockMvc.perform(delete("/api/groups/{groupId}", groupId));
        result.andExpect(status().isOk());
    }
}
