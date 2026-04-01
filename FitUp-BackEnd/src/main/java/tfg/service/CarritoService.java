package tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tfg.dto.carrito.CarritoRequest;
import tfg.dto.carrito.CarritoResponse;
import tfg.model.Carrito;
import tfg.model.Producto;
import tfg.model.Usuario;
import tfg.repository.CarritoRepository;
import tfg.repository.ProductoRepository;
import tfg.repository.UsuarioRepository;
import tfg.util.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public List<CarritoResponse> obtenerCarritoDeUsuario(Long usuarioId) {
        return carritoRepository.findAll().stream()
                .filter(c -> c.getUsuario() != null && c.getUsuario().getId().equals(usuarioId))
                .map(Mapper::toCarritoResponse)
                .collect(Collectors.toList());
    }

    public CarritoResponse agregarAlCarrito(CarritoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow();
        Producto producto = productoRepository.findById(request.getProductoId()).orElseThrow();

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setProducto(producto);
        carrito.setCantidad(request.getCantidad() != null ? request.getCantidad() : 1);

        Carrito guardado = carritoRepository.save(carrito);
        return Mapper.toCarritoResponse(guardado);
    }
    public void eliminarDelCarrito(Long idCarrito) {
        carritoRepository.deleteById(idCarrito);
    }

    public void vaciarCarritoDeUsuario(Long usuarioId) {
        List<Carrito> items = carritoRepository.findAll().stream()
                .filter(c -> c.getUsuario() != null && c.getUsuario().getId().equals(usuarioId))
                .toList();
        carritoRepository.deleteAll(items);
    }
}