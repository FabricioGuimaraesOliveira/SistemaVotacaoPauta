package com.sistema.votacao.application.adapters.controllers;

import com.sistema.votacao.application.adapters.dto.request.SessaoRequestDTO;
import com.sistema.votacao.application.adapters.dto.response.PautaResponseDTO;
import com.sistema.votacao.domain.port.sessao.SessaoServicePort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"SESSAO"})
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SessionController {

    private final ModelMapper modelMapper;
    private final SessaoServicePort sessaoServicePort;

    @Value("${tempo.sessao.default}")
    private Integer tempoPadraoSegundos;

    @PostMapping(path = "/sessao/{pautaId}/iniciar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Criação de Pauta", notes = "Criação de Pauta")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "sucess", response = PautaResponseDTO.class)})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> iniciarSessao(@PathVariable(value = "pautaId") Long pautaId,
                                                @RequestBody SessaoRequestDTO sessaoRequestDTO) {
        sessaoServicePort.iniciarSessao(pautaId, (sessaoRequestDTO.getTempoSessao() == null) ? tempoPadraoSegundos : sessaoRequestDTO.getTempoSessao());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
