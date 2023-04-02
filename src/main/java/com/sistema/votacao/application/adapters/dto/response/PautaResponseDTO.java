package com.sistema.votacao.application.adapters.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;

@Data
@EqualsAndHashCode
@ToString
public class PautaResponseDTO {

    private String id;

    private String nome;

    private HashSet resultado;
}