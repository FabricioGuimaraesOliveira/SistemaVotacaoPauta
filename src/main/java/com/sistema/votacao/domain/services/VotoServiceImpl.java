package com.sistema.votacao.domain.services;

import com.sistema.votacao.domain.entities.Voto;
import com.sistema.votacao.domain.port.pauta.PautaRepositoryPort;
import com.sistema.votacao.domain.port.sessao.SessaoRepositoryPort;
import com.sistema.votacao.domain.port.voto.VotoRepositoryPort;
import com.sistema.votacao.domain.port.voto.VotoServicePort;

import java.time.LocalDateTime;

public class VotoServiceImpl implements VotoServicePort {
    private final SessaoRepositoryPort sessaoRepositoryPort;
    private final PautaRepositoryPort pautaRepositoryPort;
    private final VotoRepositoryPort votoRepositoryPort;

    public VotoServiceImpl(VotoRepositoryPort votoRepositoryPort, SessaoRepositoryPort sessaoRepositoryPort, PautaRepositoryPort pautaRepositoryPort) {
        this.votoRepositoryPort = votoRepositoryPort;
        this.sessaoRepositoryPort = sessaoRepositoryPort;
        this.pautaRepositoryPort = pautaRepositoryPort;
    }

    @Override
    public void registrarVoto(Long idPauta, Voto voto) {
        var pauta = pautaRepositoryPort.findById(idPauta).orElseThrow();
        var sessao = sessaoRepositoryPort.findByPautaId(pauta).orElseThrow();
        voto.setDataHora(LocalDateTime.now());
        voto.setSessao(sessao);
        votoRepositoryPort.registrarVoto(voto);
    }
}
