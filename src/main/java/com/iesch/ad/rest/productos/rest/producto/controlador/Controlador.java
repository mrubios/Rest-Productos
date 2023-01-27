package com.iesch.ad.rest.productos.rest.producto.controlador;

import com.iesch.ad.rest.productos.rest.producto.modelos.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iesch.ad.rest.productos.rest.producto.repositorio.ProductoRepositorio;

import java.util.List;

@RestController
public class Controlador {
    @Autowired
    ProductoRepositorio productoRepositorio;

    //Error 404 si no hay productos y un 200 si hay uno o más
    //ResponseEntity devolverá un dato numérico, al ser un ? puede ser null
     @GetMapping("api/producto")
    public ResponseEntity<?> obtenerTodos(){
         List<Producto> result = productoRepositorio.findAll();
         if (result.isEmpty()){
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(result);
    }

    @GetMapping("api/producto/{id}")
    public ResponseEntity<?> obtenerUno(@PathVariable Long id){
         Producto result = productoRepositorio.findById(id).orElse(null);
         if (result==null)return ResponseEntity.notFound().build();
         else return ResponseEntity.ok(result);
    }

    @PostMapping("api/producto")
    public ResponseEntity<?> nuevoProducto(@RequestBody Producto nuevo){
        //return 201(status(HttpStatus.Created)) y producto insertado
         Producto productoSalvado = productoRepositorio.save(nuevo);
         return ResponseEntity.status(HttpStatus.CREATED).body(productoSalvado);
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
