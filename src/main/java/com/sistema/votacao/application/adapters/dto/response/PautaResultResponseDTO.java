package com.sistema.votacao.application.adapters.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PautaResultResponseDTO {
    @Schema(description = "Identificador da Pauta", example = "1")
    private Long pauta_id;
    @Schema(description = "Nome da Pauta", example = "Votação Condomínio")
    private String nome;
    @Schema(description = "Quantidade de Votos TIPO SIM", example = "2")
    private Integer qtd_sim;
    @Schema(description = "Quantidade de Votos TIPO NAO", example = "1")
    private Integer qtd_nao;
}
