package com.sistema.votacao.infrastructure.adapters.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_VOTO")
public class VotoEntity {


    @EmbeddedId
    private VotoId id;

    @Column(nullable = false, name = "tipo_voto")
    @Enumerated(EnumType.STRING)
    private VotoEscolhaUsuario votoUsuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sessao_id", insertable = false, updatable = false)
    private SessaoEntity sessao;
}
