package com.sistema.votacao.application.adapters.controllers;

import com.sistema.votacao.application.adapters.dto.response.PautaResponseDTO;
import com.sistema.votacao.domain.port.sessao.SessaoServicePort;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = {"SESSAO"})
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SessionController {

    private final SessaoServicePort sessaoServicePort;


    @GetMapping(path = "/sessao/{pautaId}/iniciar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Abertura da Sessão de Votação", notes = "Abertura da Sessão de Votação")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sessão de votação aberta", response = PautaResponseDTO.class)})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> iniciarSessao(@PathVariable(value = "pautaId") Long pautaId,
                                                @ApiParam(required = false) @RequestParam(defaultValue = "60", name = "tempoDuracaoSegundos") Long duracaoSessao) {
        log.info("Iniciando abertura da sessao com o tempo(S) : {} ", duracaoSessao.toString());
        sessaoServicePort.iniciarSessao(pautaId, duracaoSessao);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
