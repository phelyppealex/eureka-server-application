package br.ufrn.cliente.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    public ModelMapper obterModelMapper() {
        return new ModelMapper();
    }
    
}
