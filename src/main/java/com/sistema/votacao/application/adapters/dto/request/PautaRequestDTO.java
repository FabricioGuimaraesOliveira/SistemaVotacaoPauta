package com.sistema.votacao.application.adapters.dto.request;


import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PautaRequestDTO {
    @ApiModelProperty(value = "O nome da pauta que será votada",example = "Pauta de cooperativismo")
    @NotBlank(message = "O nome deve ser obrigatótio")
    private String nome;
}
