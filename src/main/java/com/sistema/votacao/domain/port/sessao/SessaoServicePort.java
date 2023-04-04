package com.sistema.votacao.domain.port.sessao;


public interface SessaoServicePort {
    void iniciarSessao(Long idPauta, Long tempoFuncionamento);

}
