package com.sistema.votacao.infrastructure.config;

import com.sistema.votacao.domain.port.pauta.PautaRepositoryPort;
import com.sistema.votacao.domain.port.sessao.SessaoRepositoryPort;
import com.sistema.votacao.domain.port.voto.VotoRepositoryPort;
import com.sistema.votacao.domain.services.PautaServiceImpl;
import com.sistema.votacao.domain.services.SessaoServiceImpl;
import com.sistema.votacao.domain.services.VotoServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {


    @Bean
    PautaServiceImpl instance(PautaRepositoryPort pautaRepositoryPort, SessaoRepositoryPort sessaoRepositoryPort) {
        return new PautaServiceImpl(pautaRepositoryPort, sessaoRepositoryPort);
    }

    @Bean
    SessaoServiceImpl instanceSessaoService(SessaoRepositoryPort sessaoRepositoryPort, PautaRepositoryPort pautaRepositoryPort) {
        return new SessaoServiceImpl(sessaoRepositoryPort, pautaRepositoryPort);
    }

    @Bean
    VotoServiceImpl instanceVotoService(VotoRepositoryPort votoRepositoryPort, SessaoRepositoryPort sessaoRepositoryPort, PautaRepositoryPort pautaRepositoryPort) {
        return new VotoServiceImpl(votoRepositoryPort, sessaoRepositoryPort, pautaRepositoryPort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}