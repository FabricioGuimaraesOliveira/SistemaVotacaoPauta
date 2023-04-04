package com.sistema.votacao.infrastructure.adapters.repository;

import com.sistema.votacao.infrastructure.adapters.entity.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringSessaoRepository extends JpaRepository<SessaoEntity, Long>, JpaSpecificationExecutor<SessaoEntity> {
    @Query(value = "select * from tb_sessao where id_pauta = :pautaId", nativeQuery = true)
    Optional<SessaoEntity> findSessaoByPauta(@Param("pautaId") Long pautaId);
}
