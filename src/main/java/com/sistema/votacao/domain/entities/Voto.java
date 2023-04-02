package com.sistema.votacao.domain.entities;

import java.time.LocalDateTime;

public class Voto {
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public VotoEscolhaUsuario getVotoEscolhaUsuario() {
        return votoEscolhaUsuario;
    }

    public void setVotoEscolhaUsuario(VotoEscolhaUsuario votoEscolhaUsuario) {
        this.votoEscolhaUsuario = votoEscolhaUsuario;
    }

    private String cpf;
    private VotoEscolhaUsuario votoEscolhaUsuario;

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    private LocalDateTime dataHora;
    private Sessao sessao;
}
