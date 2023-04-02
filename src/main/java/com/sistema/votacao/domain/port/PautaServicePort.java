package com.sistema.votacao.domain.port;

import com.sistema.votacao.domain.entities.Pauta;

import java.util.List;
import java.util.Optional;

public interface PautaServicePort {
    Pauta save(Pauta pauta);

    List<Pauta> findAll();

    Optional<Pauta> findById(Long id);
}
