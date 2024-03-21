package com.example.test2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenFromAndTo_whenIfHasDirectRoute_thenTrue() throws Exception {
        mockMvc.perform(get("/api/direct?from={from}&to={to}", 0, 3))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.direct").value(true));
    }

    @Test
    void givenFromAndTo_whenIfHasDirectRoute_thenFalse() throws Exception {
        mockMvc.perform(get("/api/direct?from={from}&to={to}", 2, 9))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.direct").value(false));
    }
}