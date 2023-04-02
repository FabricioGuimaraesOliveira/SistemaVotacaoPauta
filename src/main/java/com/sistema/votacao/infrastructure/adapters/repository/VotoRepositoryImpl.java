package com.sistema.votacao.infrastructure.adapters.repository;

import com.sistema.votacao.domain.entities.Sessao;
import com.sistema.votacao.domain.entities.Voto;
import com.sistema.votacao.domain.port.voto.VotoRepositoryPort;
import com.sistema.votacao.infrastructure.adapters.entity.VotoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VotoRepositoryImpl implements VotoRepositoryPort {

    private final SpringVotoRepository votoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public VotoRepositoryImpl(SpringVotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Transactional
    @Override
    public void registrarVoto(Voto voto) {
        VotoEntity votoEntity = modelMapper.map(voto, VotoEntity.class);
        votoRepository.save(votoEntity);

    }

    @Override
    public Boolean verificarVotoExistente(Voto voto, Sessao sessao) {
        return votoRepository.verifyVotoBySessao(voto.getCpf(), sessao.getId()).isPresent() ? Boolean.TRUE : Boolean.FALSE;
    }
}
