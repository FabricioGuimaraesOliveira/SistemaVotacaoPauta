package com.sistema.votacao.application.adapters.controllers;

import com.sistema.votacao.application.adapters.dto.request.PautaRequestDTO;
import com.sistema.votacao.application.adapters.dto.response.PautaResponseDTO;
import com.sistema.votacao.application.adapters.dto.response.PautaResultResponseDTO;
import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.port.pauta.PautaServicePort;
import com.sistema.votacao.infrastructure.adapters.service.TopicKafkaProducerService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1/pauta")
@RequiredArgsConstructor
@Tag(name = "Pauta", description = "Endpoints para controle da Pauta")
public class PautaController {

    private final ModelMapper modelMapper;
    private final PautaServicePort pautaServicePort;
    private final TopicKafkaProducerService topicKafkaProducer;

    @Operation(summary = "Criar uma Pauta",
            description = "Neste endpoint será criado uma Pauta para votação.",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PautaResponseDTO.class)),
                            responseCode = "201", description = "Pauta Criada com Sucesso."),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)),
                            responseCode = "400", description = "Erro ao criar a Pauta"
                    )
            }
    )
    @PostMapping(path = "/criar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PautaResponseDTO> criarPauta(@RequestBody @Valid PautaRequestDTO pautaDto) {
        log.info("Iniciando a criacao da pauta : {} ", pautaDto.toString());
        Pauta pauta = modelMapper.map(pautaDto, Pauta.class);
        var response = pautaServicePort.save(pauta);
        log.info("A pauta foi criada com sucesso!.");
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(response, PautaResponseDTO.class));
    }

    @Operation(summary = "Obter resultado de todas as Pautas",
            description = "Neste endpoint será retornado o resultado das votações.",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Resultado obtido com sucesso."),
                    @ApiResponse(
                            responseCode = "400", description = "Erro o obter resultado."
                    )
            }
    )

    @GetMapping(path = "/resultado", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PautaResultResponseDTO>> getResultado() {
        log.info("Buscando Resultado!.");
        var result = pautaServicePort.buscarResultadoVotacoes().stream().map(item -> modelMapper.map(item, PautaResultResponseDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Notificar resultado da pauta",
            description = "Notificar resultado da pauta via kafka.",
            responses = {
                    @ApiResponse(
                            responseCode = "200"),
                    @ApiResponse(
                            responseCode = "400"
                    )
            }
    )
    @GetMapping(path = "/{pautaId}/notificarResultado", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void enviarResultado(@Schema(description = "Id da pauta", example = "1") @PathVariable(value = "pautaId") Long pautaId) {
        log.info("Notificar no topico kafka.");
        var result = pautaServicePort.findById(pautaId);
        result.ifPresent(
                pauta -> topicKafkaProducer.sendTopicKafka(responseDTO(pauta))
        );
    }

    private PautaResponseDTO responseDTO(Pauta pauta) {
        PautaResponseDTO pautaResponseDTO = modelMapper.map(pauta, PautaResponseDTO.class);
        pautaResponseDTO.setResultado(pautaServicePort.resultado(pauta));
        return pautaResponseDTO;
    }


}
