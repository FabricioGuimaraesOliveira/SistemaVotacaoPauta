package com.sistema.votacao.application.adapters.controllers;

import com.sistema.votacao.application.adapters.dto.request.VotoRequestDTO;
import com.sistema.votacao.domain.entities.Voto;
import com.sistema.votacao.domain.port.voto.VotoServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/voto")
@RequiredArgsConstructor
@Tag(name = "Voto", description = "Endpoint para registro do voto")
public class VotoController {

    private final ModelMapper modelMapper;
    private final VotoServicePort votoServicePort;

    @Operation(summary = "Registrar o Voto",
            description = "Neste endpoint será registrado o voto.",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json"),
                            responseCode = "200", description = "Voto registrado com sucesso."),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)),
                            responseCode = "400", description = "Erro ao registrar o voto."
                    )
            }
    )
    @PostMapping(path = "/{pautaId}/votar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void votar(@Schema(description = "Id da pauta") @PathVariable(value = "pautaId") Long pautaId,
                      @RequestBody @Valid VotoRequestDTO votoRequestDTO) {
        log.info("Registrando Voto : {} ", votoRequestDTO.toString());
        Voto voto = modelMapper.map(votoRequestDTO, Voto.class);
        votoServicePort.registrarVoto(pautaId, voto);
        log.info("Voto Registrado com sucesso!");

    }
}
