package com.sistema.votacao.infrastructure.adapters.repository;

import com.sistema.votacao.infrastructure.adapters.entity.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SpringVotoRepository extends JpaRepository<VotoEntity, Long>, JpaSpecificationExecutor<VotoEntity> {
    @Query(value = "select * from tb_voto where cpf=:votoCpf and sessao_id=:sessaoId", nativeQuery = true)
    Optional<VotoEntity> verifyVotoBySessao(@Param("votoCpf") String cpf, @Param("sessaoId") Long sessaoId);
}
