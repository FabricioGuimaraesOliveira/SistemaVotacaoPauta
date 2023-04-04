package com.sistema.votacao.application.adapters.controllers;

import com.sistema.votacao.domain.port.sessao.SessaoServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Sessao", description = "Endpoint para controle da Sessao")
public class SessionController {

    private final SessaoServicePort sessaoServicePort;

    @Operation(summary = "Iniciar a sessão de uma Pauta",
            description = "Neste endpoint será iniciado a sessão para a votação de uma pauta.",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json"),
                            responseCode = "200"),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)),
                            responseCode = "400", description = "Erro ao iniciar sessão"
                    ),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)),
                            responseCode = "404", description = "Erro ao iniciar sessão.A pauta não foi encontrada."
                    ),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)),
                            responseCode = "409", description = "Erro ao iniciar sessão.Sessão duplicada."
                    )
            }
    )
    @GetMapping(path = "/sessao/{pautaId}/iniciar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void iniciarSessao(@Schema(description = "Id da pauta", example = "1") @PathVariable(value = "pautaId") Long pautaId,
                              @Schema(description = "Tempo de duração da Sessão em segundos", example = "60") @RequestParam(defaultValue = "60", name = "duracao") Long duracaoSessao) {
        log.info("Iniciando abertura da sessao com o tempo(S) : {} ", duracaoSessao.toString());
        sessaoServicePort.iniciarSessao(pautaId, duracaoSessao);
    }
}
