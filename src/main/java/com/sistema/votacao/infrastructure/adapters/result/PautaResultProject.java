package com.sistema.votacao.infrastructure.adapters.result;

public interface PautaResultProject {
    Long getPauta_id();

    String getNome();

    Integer getQtd_sim();

    Integer getQtd_nao();
}
