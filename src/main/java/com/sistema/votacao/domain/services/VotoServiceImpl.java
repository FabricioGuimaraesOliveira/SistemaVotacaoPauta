package com.sistema.votacao.domain.services;

import com.sistema.votacao.application.adapters.exception.BusinessException;
import com.sistema.votacao.application.adapters.exception.ResourceNotFoundException;
import com.sistema.votacao.domain.entities.Sessao;
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

        var pauta = pautaRepositoryPort.findById(idPauta).orElseThrow(() -> new ResourceNotFoundException("Nao foi possivel encontrar a pauta : " + idPauta));
        var sessao = sessaoRepositoryPort.findByPautaId(pauta).orElseThrow(() -> new ResourceNotFoundException("Nao foi possivel encontrar a sessao relacionado a pauta : " + idPauta));

        validarHorarioVotacao(sessao);
        validarVotoExistente(voto, sessao);

        voto.setDataHora(LocalDateTime.now());
        voto.setSessao(sessao);
        votoRepositoryPort.registrarVoto(voto);
    }

    private void validarHorarioVotacao(Sessao sessao) {
        if (LocalDateTime.now().isAfter(sessao.getDataFechamento())) {
            throw new BusinessException("A sessão já está encerrada.!");
        }
    }

    private void validarVotoExistente(Voto voto, Sessao sessao) {
        if (votoRepositoryPort.verificarVotoExistente(voto, sessao)) {
           throw  new BusinessException("Este voto já foi registrado. ");
        }

    }

}
