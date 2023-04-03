package com.sistema.votacao.application.adapters.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PautaRequestDTO {
    @Schema(description = "Nome da Pauta a ser criada", example = "Votação Condomínio")
    @NotBlank(message = "O nome deve ser obrigatótio")
    private String nome;
}
