package com.iesch.ad.rest.productos.rest.producto.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiConfiguracion {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
