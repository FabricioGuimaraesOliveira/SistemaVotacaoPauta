package com.sistema.votacao.application.adapters.controllers;

import com.sistema.votacao.application.adapters.dto.request.PautaRequestDTO;
import com.sistema.votacao.application.adapters.dto.response.PautaResponseDTO;
import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.port.pauta.PautaServicePort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = {"PAUTA"})
@RequestMapping("/v1/pauta")
@RequiredArgsConstructor
public class PautaController {

    private final ModelMapper modelMapper;
    private final PautaServicePort pautaServicePort;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Criação de Pauta", notes = "Criação de Pauta")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "created", response = PautaResponseDTO.class)})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PautaResponseDTO> criarPauta(@RequestBody @Valid PautaRequestDTO pautaDto) {
        log.info("Iniciando a criacao da pauta : {} ", pautaDto.toString());
        Pauta pauta = modelMapper.map(pautaDto, Pauta.class);
        var response = pautaServicePort.save(pauta);
        log.info("A pauta foi criada com sucesso!.");
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(response, PautaResponseDTO.class));
    }


}
