package com.sistema.votacao.infrastructure.config;

import com.sistema.votacao.domain.port.pauta.PautaRepositoryPort;
import com.sistema.votacao.domain.port.sessao.SessaoRepositoryPort;
import com.sistema.votacao.domain.services.PautaServiceImpl;
import com.sistema.votacao.domain.services.SessaoServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {


    @Bean
    PautaServiceImpl instance(PautaRepositoryPort pautaRepositoryPort) {
        return new PautaServiceImpl(pautaRepositoryPort);
    }

    @Bean
    SessaoServiceImpl instanceSessaoService(SessaoRepositoryPort sessaoRepositoryPort, PautaRepositoryPort pautaRepositoryPort) {
        return new SessaoServiceImpl(sessaoRepositoryPort, pautaRepositoryPort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}