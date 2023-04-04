package com.sistema.votacao.application.adapters.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.votacao.domain.port.sessao.SessaoServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
public class SessaoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SessaoServicePort sessaoServicePort;

    @BeforeEach
    void setUp() {

        doNothing().when(sessaoServicePort).iniciarSessao(1L, 60L);
    }

    @Test
    public void iniciarSessaoShouldReturnHttpStatusOk() throws Exception {

        ResultActions result = mockMvc.perform(get("/v1/sessao/1/iniciar")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

    }

}
