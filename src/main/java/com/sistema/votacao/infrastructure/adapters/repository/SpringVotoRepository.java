package com.sistema.votacao.infrastructure.adapters.repository;

import com.sistema.votacao.infrastructure.adapters.entity.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
interface SpringVotoRepository extends JpaRepository<VotoEntity, Long>, JpaSpecificationExecutor<VotoEntity> {
}
