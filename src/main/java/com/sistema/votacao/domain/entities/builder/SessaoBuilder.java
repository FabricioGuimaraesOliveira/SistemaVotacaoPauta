package com.sistema.votacao.domain.entities.builder;

import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.entities.Sessao;
import com.sistema.votacao.domain.entities.Voto;

import java.time.LocalDateTime;
import java.util.Set;

public class SessaoBuilder implements Builder {
    private Sessao sessao;

    public SessaoBuilder() {
        this.sessao = new Sessao();
    }

    @Override
    public void id(Long id) {
        this.sessao.setId(id);
    }

    @Override
    public void dataAbertura(LocalDateTime dataAbertura) {
        this.sessao.setDataAbertura(dataAbertura);
    }

    @Override
    public void dataFechamento(LocalDateTime dataFechamento) {
        this.sessao.setDataFechamento(dataFechamento);
    }

    @Override
    public void pauta(Pauta pauta) {
        this.sessao.setPauta(pauta);
    }

    @Override
    public void votos(Set<Voto> votos) {
        this.sessao.setVotos(votos);
    }


    @Override
    public Sessao getResult() {
        return this.sessao;
    }
}