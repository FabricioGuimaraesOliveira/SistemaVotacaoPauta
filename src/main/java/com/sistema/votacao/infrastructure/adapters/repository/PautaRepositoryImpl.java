package com.sistema.votacao.infrastructure.adapters.repository;

import com.sistema.votacao.domain.entities.Pauta;
import com.sistema.votacao.domain.port.pauta.PautaRepositoryPort;
import com.sistema.votacao.infrastructure.adapters.entity.PautaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PautaRepositoryImpl implements PautaRepositoryPort {

    private final SpringPautaRepository pautaRepository;

    @Autowired
    private ModelMapper modelMapper;


    public PautaRepositoryImpl(SpringPautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Transactional()
    @CacheEvict(value = "pautas", allEntries = true)
    @Override
    public Pauta save(Pauta pauta) {
        PautaEntity pautaEntity = modelMapper.map(pauta, PautaEntity.class);
        return modelMapper.map(pautaRepository.saveAndFlush(pautaEntity), Pauta.class);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "pautas")
    @Override
    public List<Pauta> findAll() {
        return pautaRepository.findAll().stream().map(pautaEntity -> {
            return modelMapper.map(pautaEntity, Pauta.class);
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Pauta> findById(Long id) {

        Optional<PautaEntity> pautaEntity = pautaRepository.findById(id);
        if (pautaEntity.isPresent()) {
            return Optional.of(modelMapper.map(pautaEntity.get(), Pauta.class));
        }
        return Optional.empty();
    }
}
