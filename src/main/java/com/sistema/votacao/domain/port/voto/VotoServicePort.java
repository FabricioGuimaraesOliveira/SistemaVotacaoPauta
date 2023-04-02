package com.sistema.votacao.domain.port.voto;

import com.sistema.votacao.domain.entities.Voto;

public interface VotoServicePort {
    void registrarVoto(Long idPauta, Voto voto);
}
