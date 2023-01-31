package com.iesch.ad.rest.productos.rest.producto.dto;

import lombok.Data;

//Data transfer object
@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String categoriaNombre;
}
