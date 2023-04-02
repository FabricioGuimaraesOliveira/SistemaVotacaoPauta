package com.sistema.votacao.infrastructure.adapters.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "sessao")
@Entity
@Table(name = "TB_VOTO")
public class VotoEntity {
    @CPF
    @NotBlank
    @Id
    private String cpf;


    @Column(nullable = false, name = "tipo_voto")
    @Enumerated(EnumType.STRING)
    private VotoEscolhaUsuario votoUsuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime data;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    private SessaoEntity sessao;
}
