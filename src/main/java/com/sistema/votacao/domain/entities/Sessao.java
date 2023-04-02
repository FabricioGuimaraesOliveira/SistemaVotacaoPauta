package com.sistema.votacao.domain.entities;

import java.time.LocalDateTime;
import java.util.Set;

public class Sessao {

    public Sessao() {
    }

    public Sessao(Long id, LocalDateTime dataAbertura, LocalDateTime dataFechamento, Pauta pauta, Set<Voto> votos) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.pauta = pauta;
        this.votos = votos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Set<Voto> getVotos() {
        return votos;
    }

    public void setVotos(Set<Voto> votos) {
        this.votos = votos;
    }

    private Long id;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private Pauta pauta;
    private Set<Voto> votos;

}
