package com.iesch.ad.rest.productos.rest.producto.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MiConfiguracion {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    //Hacer el cors global(forma cutre)
//    @Bean
//    public WebMvcConfigurer corsConfigurer(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                //WebMvcConfigurer.super.addCorsMappings(registry);
//                //Esto hace que se pueda consumir desde cualquier sitio
//                registry.addMapping("/**");
//            }
//        };
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/productoDTO/**")
                        .allowedOrigins("http://localhost:2212")
                        .allowedMethods("GET", "POST")
                        .maxAge(3600L);
            }
        };
    }
}
