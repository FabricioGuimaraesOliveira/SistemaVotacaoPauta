package com.sistema.votacao.application.adapters.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@Data
@EqualsAndHashCode
@ToString
public class PautaResponseDTO {
@Schema(description = "Identificador da Pauta",example = "1")
    private String id;
    @Schema(description = "Nome da Pauta",example = "Votação Condomínio")
    private String nome;
    @Schema(description = "Resultado da votação")
    private Map<String, Long> resultado;
}