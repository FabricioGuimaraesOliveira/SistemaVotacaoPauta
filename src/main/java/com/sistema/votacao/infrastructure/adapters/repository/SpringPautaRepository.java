package com.sistema.votacao.infrastructure.adapters.repository;

import com.sistema.votacao.infrastructure.adapters.entity.PautaEntity;
import com.sistema.votacao.infrastructure.adapters.result.PautaResultProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringPautaRepository extends JpaRepository<PautaEntity, Long>, JpaSpecificationExecutor<PautaEntity> {
    @Query(value = "Select  tb_pauta.id as pauta_id,nome,sum(case when tipo_voto = 'SIM' then 1 else 0 end) as qtd_sim,sum(case when tipo_voto = 'NAO' then 1 else 0 end) as qtd_nao from tb_pauta LEFT JOIN tb_sessao on tb_pauta.id=tb_sessao.id_pauta LEFT JOIN  tb_voto on sessao_id=tb_sessao.id GROUP BY pauta_id,nome ORDER BY pauta_id", nativeQuery = true)
    List<PautaResultProject> getAllResults();
}
