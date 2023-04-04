package com.sistema.votacao.application.adapters.dto.request;

import com.sistema.votacao.domain.entities.VotoEscolhaUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

@Data
@EqualsAndHashCode
@ToString
public class VotoRequestDTO {

    @Schema(description = "CPF do Associado", example = "345.117.800-13")
    @NotNull(message = "CPF do Associado não pode ser nullo")
    @CPF
    private String cpf;


    @NotNull(message = "A escolha é obrigatório e precisa seguir o padrão: SIM/NAO")
    private VotoEscolhaUsuario votoEscolhaUsuario;
}
