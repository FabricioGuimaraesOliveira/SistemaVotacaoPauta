package com.sistema.votacao.application.adapters.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.votacao.application.adapters.dto.request.VotoRequestDTO;
import com.sistema.votacao.domain.entities.Voto;
import com.sistema.votacao.domain.entities.VotoEscolhaUsuario;
import com.sistema.votacao.domain.port.voto.VotoServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VotoController.class)
public class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VotoServicePort votoServicePort;
    @MockBean
    private ModelMapper modelMapper;

    Voto voto;
    VotoRequestDTO votoRequestDTO;

    @BeforeEach
    void setUp() {
        voto = new Voto();
        doNothing().when(votoServicePort).registrarVoto(1L, voto);
        votoRequestDTO = new VotoRequestDTO();
        votoRequestDTO.setCpf("280.889.240-30");
        votoRequestDTO.setVotoEscolhaUsuario(VotoEscolhaUsuario.SIM);
    }

    @Test
    public void registerVotoShouldReturnHttpStatusOk() throws Exception {


        String jsonBody = objectMapper.writeValueAsString(votoRequestDTO);
        ResultActions result = mockMvc.perform(post("/v1/voto/1/votar")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

    }

    @Test
    public void registerVotoShouldReturnHttpBadRequestWhenCpfIsNull() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(new VotoRequestDTO());
        ResultActions result = mockMvc.perform(post("/v1/voto/1/votar")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());

    }
}
