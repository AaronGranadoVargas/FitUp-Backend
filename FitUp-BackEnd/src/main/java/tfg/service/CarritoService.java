package tfg.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarritoService {

    @Autowired private CarritoRepository carritoRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ProductoRepository productoRepository;

    private Usuario getUsuarioAutenticado() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<CarritoResponse> obtenerCarritoDeUsuario() {
        Usuario usuario = getUsuarioAutenticado();
        return carritoRepository.findByUsuarioId(usuario.getId()).stream()
                .map(Mapper::toCarritoResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CarritoResponse agregarAlCarrito(CarritoRequest request) {
        Usuario usuario = getUsuarioAutenticado();
        Producto producto = productoRepository.findById(request.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Optional<Carrito> existente = carritoRepository.findByUsuarioIdAndProductoId(usuario.getId(), producto.getId());

        Carrito carrito;
        if (existente.isPresent()) {
            carrito = existente.get();
            int sumar = request.getCantidad() != null ? request.getCantidad() : 1;
            carrito.setCantidad(carrito.getCantidad() + sumar);
        } else {
            carrito = new Carrito();
            carrito.setUsuario(usuario);
            carrito.setProducto(producto);
            carrito.setCantidad(request.getCantidad() != null ? request.getCantidad() : 1);
        }

        return Mapper.toCarritoResponse(carritoRepository.save(carrito));
    }

    public void eliminarDelCarrito(Long idCarrito) {
        carritoRepository.deleteById(idCarrito);
    }

    @Transactional
    public void vaciarCarritoDeUsuario() {
        Usuario usuario = getUsuarioAutenticado();
        List<Carrito> items = carritoRepository.findByUsuarioId(usuario.getId());
        carritoRepository.deleteAll(items);
    }
}