package com.iesch.ad.rest.productos.rest.producto.dto;

import lombok.Data;

@Data
public class CreateProductoDTO {
    private String nombre;
    private float precio;
    private Long categoriaId;
}
