package com.iesch.ad.rest.productos.rest.producto.repositorio;

import com.iesch.ad.rest.productos.rest.producto.modelos.Categoria;
import com.iesch.ad.rest.productos.rest.producto.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> { }
