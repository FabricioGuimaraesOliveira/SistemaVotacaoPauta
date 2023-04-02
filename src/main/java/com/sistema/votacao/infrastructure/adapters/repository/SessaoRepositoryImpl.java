package com.sistema.votacao.infrastructure.adapters.repository;

import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.entities.Sessao;
import com.sistema.votacao.domain.port.sessao.SessaoRepositoryPort;
import com.sistema.votacao.infrastructure.adapters.entity.SessaoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class SessaoRepositoryImpl implements SessaoRepositoryPort {
    private final SpringSessaoRepository sessaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public SessaoRepositoryImpl(SpringSessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    @Transactional
    @Override
    public void iniciarSessao(Sessao sessao) {
        SessaoEntity sessaoEntity = modelMapper.map(sessao, SessaoEntity.class);
        sessaoRepository.save(sessaoEntity);
    }

    @Override
    public Optional<Sessao> findByPautaId(Pauta pauta) {

        Optional<SessaoEntity> sessaoEntity = sessaoRepository.findSessaoByPauta(pauta.getId());
        if (sessaoEntity.isPresent()) {
            return Optional.of(modelMapper.map(sessaoEntity.get(), Sessao.class));
        }
        return Optional.empty();
    }
}
