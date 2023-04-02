package com.sistema.votacao.application.adapters.controllers;

import com.sistema.votacao.application.adapters.dto.request.PautaRequestDTO;
import com.sistema.votacao.application.adapters.dto.response.PautaResponseDTO;
import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.port.PautaServicePort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"VOTACAO"})
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
        Pauta pauta = modelMapper.map(pautaDto, Pauta.class);
        pautaServicePort.save(pauta);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(pauta, PautaResponseDTO.class));
    }


}
