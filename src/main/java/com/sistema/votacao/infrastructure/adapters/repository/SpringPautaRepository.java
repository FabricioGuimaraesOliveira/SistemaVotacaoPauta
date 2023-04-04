package com.sistema.votacao.infrastructure.adapters.repository;

import com.sistema.votacao.infrastructure.adapters.entity.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringPautaRepository extends JpaRepository<PautaEntity, Long>, JpaSpecificationExecutor<PautaEntity> {
}
