package com.sistema.votacao.infrastructure.adapters.repository;

import com.sistema.votacao.infrastructure.adapters.entity.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringSessaoRepository extends JpaRepository<SessaoEntity, Long>, JpaSpecificationExecutor<SessaoEntity> {
}
