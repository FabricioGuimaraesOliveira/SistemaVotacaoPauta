package com.sistema.votacao.infrastructure.adapters.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PautaResult {
    private Long pauta_id;
    private String nome;
    private Integer qtd_sim;
    private Integer qtd_nao;
}
