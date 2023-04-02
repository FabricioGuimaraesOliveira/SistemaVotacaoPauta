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
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = {"VOTACAO"})
@RequestMapping("/v1/voto")
@RequiredArgsConstructor
public class VotoController {

    private final ModelMapper modelMapper;
    private final VotoServicePort votoServicePort;


    @PostMapping(path = "/{pautaId}/votar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registrando Voto", notes = "Registrando Voto")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Voto registrado", response = PautaResponseDTO.class)})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> votar(@PathVariable(value = "pautaId") Long pautaId,
                                        @RequestBody @Valid VotoRequestDTO votoRequestDTO) {
        log.info("Registrando Voto : {} ", votoRequestDTO.toString());
        Voto voto = modelMapper.map(votoRequestDTO, Voto.class);
        votoServicePort.registrarVoto(pautaId, voto);
        log.info("Voto Registrado com sucesso!");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
