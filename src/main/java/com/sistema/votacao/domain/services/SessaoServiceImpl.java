package com.sistema.votacao.domain.services;

import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.entities.Sessao;
import com.sistema.votacao.domain.port.pauta.PautaRepositoryPort;
import com.sistema.votacao.domain.port.sessao.SessaoRepositoryPort;
import com.sistema.votacao.domain.port.sessao.SessaoServicePort;

import java.time.LocalDateTime;
import java.util.Optional;

public class SessaoServiceImpl implements SessaoServicePort {
    private final SessaoRepositoryPort sessaoRepositoryPort;
    private final PautaRepositoryPort pautaRepositoryPort;


    public SessaoServiceImpl(SessaoRepositoryPort sessaoRepositoryPort, PautaRepositoryPort pautaRepositoryPort) {
        this.sessaoRepositoryPort = sessaoRepositoryPort;
        this.pautaRepositoryPort = pautaRepositoryPort;
    }

    private Optional<Sessao> findByPautaId(Pauta pauta) {
        return sessaoRepositoryPort.findByPautaId(pauta);
    }


    @Override
    public void iniciarSessao(Long idPauta, Long tempoFuncionamento) {
        var pauta = pautaRepositoryPort.findById(idPauta).orElseThrow();
        if (findByPautaId(pauta).isPresent()) {

        }

        LocalDateTime dataAbertura = LocalDateTime.now();

        Sessao sessao = new Sessao();
        sessao.setDataAbertura(dataAbertura);
        sessao.setPauta(pauta);
        sessao.setDataFechamento(getDataFechamento(dataAbertura, tempoFuncionamento));

        sessaoRepositoryPort.iniciarSessao(sessao);

    }

    private LocalDateTime getDataFechamento(LocalDateTime dataAbertura, Long tempoFuncionamento) {
        return dataAbertura.plusSeconds(tempoFuncionamento);
    }

}
