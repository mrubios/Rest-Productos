package com.iesch.ad.rest.productos.rest.producto.repositorio;

import com.iesch.ad.rest.productos.rest.producto.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> { }
