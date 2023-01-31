package com.iesch.ad.rest.productos.rest.producto.converter;

import com.iesch.ad.rest.productos.rest.producto.dto.CreateProductoDTO;
import com.iesch.ad.rest.productos.rest.producto.dto.ProductoDTO;
import com.iesch.ad.rest.productos.rest.producto.modelos.Producto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //Tiene que ser final
public class ProductoDTOConverter {
    private final ModelMapper modelMapper;

    public ProductoDTO convertToDTO(Producto producto){
        return modelMapper.map(producto, ProductoDTO.class);
        //Los nombres han de ser exactamente los mismos para que esta linea funcione
    }
    public  Producto convertDesdeDTO(CreateProductoDTO createProductoDTO){
        return modelMapper.map(createProductoDTO, Producto.class);
    }
}
