package com.iesch.ad.rest.productos.rest.producto.controlador;

import com.iesch.ad.rest.productos.rest.producto.modelos.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iesch.ad.rest.productos.rest.producto.repositorio.ProductoRepositorio;

import java.util.List;

@RestController
public class Controlador {
    @Autowired
    ProductoRepositorio productoRepositorio;

     @GetMapping("api/producto")
    public List<Producto> obtenerTodos(){
         return productoRepositorio.findAll();
    }

    @GetMapping("api/producto/{id}")
    public Producto obtenerUno(@PathVariable Long id){
         return productoRepositorio.findById(id).orElse(null);
    }

    @PostMapping("api/producto")
    public Producto nuevoProducto(@RequestBody Producto nuevo){
         return productoRepositorio.save(nuevo);
    }

    @PutMapping("api/producto/{id}")
    public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id){
         if(productoRepositorio.existsById(id)){
             editar.setId(id);
             return productoRepositorio.save(editar);
         }else return null;
    }

    @DeleteMapping("api/producto/{id}")
    public Producto borraProducto (@PathVariable Long id){
        if (productoRepositorio.existsById(id)){
            Producto result = productoRepositorio.findById(id).get();
            productoRepositorio.deleteById(id);
            return result;
        }else return null;
    }


}
