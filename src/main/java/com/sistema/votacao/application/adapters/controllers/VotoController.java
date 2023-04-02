package com.sistema.votacao.application.adapters.controllers;

import com.sistema.votacao.application.adapters.dto.request.VotoRequestDTO;
import com.sistema.votacao.application.adapters.dto.response.PautaResponseDTO;
import com.sistema.votacao.domain.entities.Voto;
import com.sistema.votacao.domain.port.voto.VotoServicePort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"VOTACAO"})
@RequestMapping("/v1/voto")
@RequiredArgsConstructor
public class VotoController {

    private final ModelMapper modelMapper;
    private final VotoServicePort votoServicePort;

    @Value("${tempo.sessao.default}")
    private Integer tempoPadraoSegundos;

    @PostMapping(path = "/{pautaId}/votar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Criação de Pauta", notes = "Criação de Pauta")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "sucess", response = PautaResponseDTO.class)})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> votar(@PathVariable(value = "pautaId") Long pautaId,
                                        @RequestBody @Valid VotoRequestDTO votoRequestDTO) {
        Voto voto = modelMapper.map(votoRequestDTO, Voto.class);
        votoServicePort.registrarVoto(pautaId, voto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
