package com.sistema.votacao.infrastructure.config;

import com.sistema.votacao.domain.port.PautaRepositoryPort;
import com.sistema.votacao.domain.port.PautaServicePort;
import com.sistema.votacao.domain.services.PautaServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {


    @Bean
    PautaServiceImpl instance(PautaRepositoryPort pautaRepositoryPort) {
        return new PautaServiceImpl(pautaRepositoryPort);
    }

//    @Bean
//    PautaServicePort pautaService(PautaRepositoryPort pautaRepositoryPort) {
//        return new PautaServiceImpl(pautaRepositoryPort);
//    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}