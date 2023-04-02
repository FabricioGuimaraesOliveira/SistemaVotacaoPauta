package com.sistema.votacao.domain.port.voto;

import com.sistema.votacao.domain.entities.Sessao;
import com.sistema.votacao.domain.entities.Voto;

public interface VotoRepositoryPort {
    void registrarVoto(Voto voto);
    Boolean verificarVotoExistente(Voto voto, Sessao sessao);
}
