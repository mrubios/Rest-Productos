package com.iesch.ad.rest.productos.rest.producto.controlador;

import com.iesch.ad.rest.productos.rest.producto.modelos.Categoria;
import com.iesch.ad.rest.productos.rest.producto.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaControlador {
    @Autowired
    CategoriaRepositorio categoriaRepositorio;

    //Error 404 si no hay categorias y un 200 si hay uno o más
    //ResponseEntity devolverá un dato numérico, al ser un ? puede ser null
     @GetMapping("api/categoria")
    public ResponseEntity<?> obtenerTodos(){
         List<Categoria> result = categoriaRepositorio.findAll();
         if (result.isEmpty()){
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(result);
    }

    @GetMapping("api/categoria/{id}")
    public ResponseEntity<?> obtenerUno(@PathVariable Long id){
         Categoria result = categoriaRepositorio.findById(id).orElse(null);
         if (result==null)return ResponseEntity.notFound().build();
         else return ResponseEntity.ok(result);
    }

    @PostMapping("api/categoria")
    public ResponseEntity<?> nuevoCategoria(@RequestBody Categoria nuevo){
        //return 201(status(HttpStatus.Created)) y categoria insertado
         Categoria categoriaSalvado = categoriaRepositorio.save(nuevo);
         return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalvado);
    }

    @PutMapping("api/categoria/{id}")
    public ResponseEntity<?> editarCategoria(@RequestBody Categoria editar, @PathVariable Long id){
//         return categoriaRepositorio.findById(id).map(categoria -> {
//            categoria.setNombre(editar.getNombre());
//            categoria.setPrecio(editar.getPrecio());
//            return ResponseEntity.ok(categoriaRepositorio.save(categoria));
//         }).orElseGet(()->{
//             return ResponseEntity.notFound().build();
//         });

        if (categoriaRepositorio.existsById(id)){
            editar.setId(id);
            Categoria actualizado = categoriaRepositorio.save(editar);
            return ResponseEntity.ok(actualizado);
        }else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("api/categoria/{id}")
    public ResponseEntity<?> borraCategoria (@PathVariable Long id){
            categoriaRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();

    }


}
