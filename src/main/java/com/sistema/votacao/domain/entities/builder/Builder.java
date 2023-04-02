package com.sistema.votacao.domain.entities.builder;

import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.entities.Sessao;
import com.sistema.votacao.domain.entities.Voto;

import java.time.LocalDateTime;
import java.util.Set;

public interface Builder {

    public void id(Long id);

    public void dataAbertura(LocalDateTime dataAbertura);

    public void dataFechamento(LocalDateTime dataFechamento);

    public void pauta(Pauta pauta);

    public void votos(Set<Voto> votos);

    Sessao getResult();
}
