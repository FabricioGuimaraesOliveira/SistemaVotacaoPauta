package com.sistema.votacao.application.adapters.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.votacao.application.adapters.dto.request.PautaRequestDTO;
import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.port.pauta.PautaServicePort;
import com.sistema.votacao.infrastructure.adapters.service.TopicKafkaProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PautaController.class)
public class PautaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PautaServicePort pautaServicePort;
    @MockBean
    private TopicKafkaProducerService topicKafkaProducer;
    @MockBean
    private ModelMapper modelMapper;

    PautaRequestDTO pautaRequestDTO;
    Pauta pauta;

    @BeforeEach
    void setUp() {
        pauta = new Pauta();
        when(pautaServicePort.save(any())).thenReturn(pauta);

        pautaRequestDTO = new PautaRequestDTO();
        pautaRequestDTO.setNome("teste");
    }

    @Test
    public void createPautaShouldReturnHttpStatusCreated() throws Exception {


        String jsonBody = objectMapper.writeValueAsString(pautaRequestDTO);
        ResultActions result = mockMvc.perform(post("/v1/pauta/criar")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());

    }

    @Test
    public void createPautaShouldReturnHttpStatusBadRequestWhenNameIsNull() throws Exception {


        String jsonBody = objectMapper.writeValueAsString(new PautaRequestDTO());
        ResultActions result = mockMvc.perform(post("/v1/pauta/criar")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());

    }

    @Test
    public void getResultVotoShouldReturnHttpStatusOk() throws Exception {

        ResultActions result = mockMvc.perform(get("/v1/pauta/resultado")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

    }
}
