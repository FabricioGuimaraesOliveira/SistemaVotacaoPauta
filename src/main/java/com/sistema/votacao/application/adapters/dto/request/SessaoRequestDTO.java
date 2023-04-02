package com.sistema.votacao.application.adapters.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class SessaoRequestDTO {

    @ApiModelProperty(value = "Tempo(S) de funcionamento da Sessão", example = "1")
    private Long tempoSessao;
}
