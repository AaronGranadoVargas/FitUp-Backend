package tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tfg.dto.producto.ProductoRequest;
import tfg.dto.producto.ProductoResponse;
import tfg.model.Producto;
import tfg.repository.ProductoRepository;
import tfg.util.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoResponse> obtenerTodos() {
        return productoRepository.findAll().stream()
                .map(Mapper::toProductoResponse)
                .collect(Collectors.toList());
    }

    public ProductoResponse crearProducto(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoria(request.getCategoria());
        producto.setImagenUrl(request.getImagenUrl());

        Producto guardado = productoRepository.save(producto);
        return Mapper.toProductoResponse(guardado);
    }
}