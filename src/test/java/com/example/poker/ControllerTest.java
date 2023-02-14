package com.example.poker;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import com.example.poker.controller.MainController;
import com.example.poker.service.Eval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Arrays;
import java.util.List;

@WebMvcTest(MainController.class)
public class ControllerTest {

    @MockBean
    private Eval service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIsStraight_ValidInput_Returns200() throws Exception {
        List<String> cards = Arrays.asList("2H", "3D", "4C", "5S", "6C", "7C", "8S");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(cards);

        when(service.isStraight(any())).thenReturn(true);
        mockMvc.perform(post("/v1/poker/is-straight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testAhandWithNoStaight() throws Exception {
        mockMvc.perform(post("/v1/poker/is-straight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"9D\", \"8C\",\"2S\", \"3C\", \"QH\", \"5D\", \"6C\"]")).
                andExpect(status().isOk());
    }

}
