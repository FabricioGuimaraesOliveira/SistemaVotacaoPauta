package com.sistema.votacao.application.adapters.dto.request;

import com.sistema.votacao.domain.entities.VotoEscolhaUsuario;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class VotoRequestDTO {

    @ApiModelProperty(value = "CPF do Associado", example = "12345678")
    @NotNull(message = "CPF do Associado")
    private String cpf;

    @ApiModelProperty(value = "Mensagem de escolha do Associado", example = "SIM")
    @NotNull(message = "A escolha é obrigatório e precisa seguir o padrão: SIM/NAO")
    private VotoEscolhaUsuario votoEscolhaUsuario;
}