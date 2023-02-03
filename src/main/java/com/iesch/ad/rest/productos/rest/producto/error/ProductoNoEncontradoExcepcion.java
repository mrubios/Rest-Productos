package com.iesch.ad.rest.productos.rest.producto.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNoEncontradoExcepcion extends RuntimeException{
    //Identificador unico para la red
    private static final long SerialVersionUID=10l;
    public ProductoNoEncontradoExcepcion(Long id){
        super("No se ha podido encontrar ningún registro con ese parámetro " + id);
    }
}
