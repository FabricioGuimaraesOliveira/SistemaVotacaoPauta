package com.sistema.votacao.domain.port.sessao;

import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.entities.Sessao;

import java.util.Optional;

public interface SessaoRepositoryPort {
    void iniciarSessao(Sessao sessao);

    Optional<Sessao> findByPautaId(Pauta pauta);
}
