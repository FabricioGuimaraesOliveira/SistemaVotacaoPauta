package com.sistema.votacao.infrastructure.adapters.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class VotoId implements Serializable {
    @Column(name="cpf")
    private String cpf;
    @Column(name="sessao_id")
    private Long sessaoId;
}
