package com.sistema.votacao.infrastructure.adapters.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class VotoId implements Serializable {
    @CPF
    @NotBlank
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "sessao_id")
    private Long sessaoId;
}
