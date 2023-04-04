package com.sistema.votacao.domain.services;

import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.entities.Sessao;
import com.sistema.votacao.domain.entities.Voto;
import com.sistema.votacao.domain.port.pauta.PautaRepositoryPort;
import com.sistema.votacao.domain.port.pauta.PautaServicePort;
import com.sistema.votacao.domain.port.sessao.SessaoRepositoryPort;

import java.util.*;

public class PautaServiceImpl implements PautaServicePort {

    private PautaRepositoryPort pautaRepositoryPort;
    private SessaoRepositoryPort sessaoRepositoryPort;

    public PautaServiceImpl(PautaRepositoryPort pautaRepositoryPort, SessaoRepositoryPort sessaoRepositoryPort) {
        this.pautaRepositoryPort = pautaRepositoryPort;
        this.sessaoRepositoryPort = sessaoRepositoryPort;
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

    private Optional<Sessao> getSessaoVotacao(Pauta pauta) {
        return sessaoRepositoryPort.findByPautaId(pauta);
    }


    @Override
    public Map<String, Long> resultado(Pauta pauta) {
        Set<Voto> votos = getSessaoVotacao(pauta).isPresent() ? getSessaoVotacao(pauta).get().getVotos() : new HashSet<>();

        Map<String, Long> result = new HashMap<>();
        result.put("SIM", votos.stream().filter(v -> v.getVotoEscolhaUsuario().toString().equalsIgnoreCase("SIM")).count());
        result.put("NAO", votos.stream().filter(v -> v.getVotoEscolhaUsuario().toString().equalsIgnoreCase("NAO")).count());

        return result;
    }

}
