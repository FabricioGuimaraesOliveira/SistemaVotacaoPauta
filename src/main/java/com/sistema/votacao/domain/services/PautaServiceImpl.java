package com.sistema.votacao.domain.services;

import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.port.PautaRepositoryPort;
import com.sistema.votacao.domain.port.PautaServicePort;

import java.util.List;
import java.util.Optional;

public class PautaServiceImpl implements PautaServicePort {

    private PautaRepositoryPort pautaRepositoryPort;

    public PautaServiceImpl(PautaRepositoryPort pautaRepositoryPort) {
        this.pautaRepositoryPort = pautaRepositoryPort;
    }

    @Override
    public Pauta save(Pauta pauta) {
        return pautaRepositoryPort.save(pauta);
    }

    @Override
    public List<Pauta> findAll() {
        return pautaRepositoryPort.findAll();
    }

    @Override
    public Optional<Pauta> findById(Long id) {
        return pautaRepositoryPort.findById(id);
    }
}
