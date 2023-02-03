package com.iesch.ad.rest.productos.rest.producto.controlador;

import com.iesch.ad.rest.productos.rest.producto.converter.ProductoDTOConverter;
import com.iesch.ad.rest.productos.rest.producto.dto.CreateProductoDTO;
import com.iesch.ad.rest.productos.rest.producto.dto.ProductoDTO;
import com.iesch.ad.rest.productos.rest.producto.error.ProductoNoEncontradoExcepcion;
import com.iesch.ad.rest.productos.rest.producto.modelos.Producto;
import com.iesch.ad.rest.productos.rest.producto.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iesch.ad.rest.productos.rest.producto.repositorio.ProductoRepositorio;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controlador {
    @Autowired
    ProductoRepositorio productoRepositorio;

    @Autowired
    ProductoDTOConverter productoDTOConverter;

    @Autowired
    CategoriaRepositorio categoriaRepositorio;

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
    public Producto obtenerUno(@PathVariable Long id){
         return productoRepositorio.findById(id).orElseThrow(() -> new ProductoNoEncontradoExcepcion(id));
    }

    @PostMapping("api/producto")
    public ResponseEntity<?> nuevoProducto(@RequestBody Producto nuevo){
        //return 201(status(HttpStatus.Created)) y producto insertado
         Producto productoSalvado = productoRepositorio.save(nuevo);
         return ResponseEntity.status(HttpStatus.CREATED).body(productoSalvado);
    }

    @PutMapping("api/producto/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Producto editar, @PathVariable Long id){
//         return productoRepositorio.findById(id).map(producto -> {
//            producto.setNombre(editar.getNombre());
//            producto.setPrecio(editar.getPrecio());
//            return ResponseEntity.ok(productoRepositorio.save(producto));
//         }).orElseGet(()->{
//             return ResponseEntity.notFound().build();
//         });

        if (productoRepositorio.existsById(id)){
            editar.setId(id);
            Producto actualizado = productoRepositorio.save(editar);
            return ResponseEntity.ok(actualizado);
        }else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("api/producto/{id}")
    public ResponseEntity<?> borraProducto (@PathVariable Long id){
            productoRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();

    }

    @GetMapping("api/productoDTO")
    public ResponseEntity<?> obtenerTodosATravesDeDTO(){
         List<Producto> result = productoRepositorio.findAll();
         if (result.isEmpty()){
             return ResponseEntity.notFound().build();
         }else{
             List<ProductoDTO> listaDTO = result.stream().map(
                     productoDTOConverter :: convertToDTO
             ).collect(Collectors.toList());
             return ResponseEntity.ok(listaDTO);
         }
    }

    @PostMapping("api/productoDTO")
    public ResponseEntity<?> nuevoATravesDeDto(@RequestBody CreateProductoDTO nuevo){
        //return 201(status(HttpStatus.Created)) y producto insertado

//        Producto productoNuevo = new Producto();
//        productoNuevo.setNombre(nuevo.getNombre());
//        productoNuevo.setPrecio(nuevo.getPrecio());
//        Categoria categoria = categoriaRepositorio.findById(nuevo.getCategoriaId()).orElse(null);
//        productoNuevo.setCategoria(categoria);

//        return ResponseEntity.status(HttpStatus.CREATED).body(productoRepositorio.save(productoNuevo));

        Producto producto = productoDTOConverter.convertDesdeDTO(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoRepositorio.save(producto));

    }



}
