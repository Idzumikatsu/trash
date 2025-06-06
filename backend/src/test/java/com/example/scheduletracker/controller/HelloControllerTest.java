package com.example.scheduletracker.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloController.class)
@AutoConfigureMockMvc(addFilters = false) // << отключаем Spring Security-фильтры
class HelloControllerTest {

  @Autowired private MockMvc mvc;

  @MockBean private com.example.scheduletracker.config.jwt.JwtUtils utils;

  @Test
  @DisplayName("GET /api/hello возвращает приветствие")
  void shouldReturnGreeting() throws Exception {
    mvc.perform(get("/api/hello")).andExpect(status().isOk()).andExpect(content().string("Привет"));
  }
}
