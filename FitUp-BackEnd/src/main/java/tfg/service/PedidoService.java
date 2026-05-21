package tfg.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tfg.dto.pedido.PedidoResponse;
import tfg.model.*;
import tfg.repository.*;
import tfg.util.Mapper;

import java.util.List;

@Service
public class PedidoService {

    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private CarritoRepository carritoRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @Autowired private ProductoRepository productoRepository;

    private Usuario getUsuarioAutenticado() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByEmail(email).orElseThrow();
    }

    @Transactional
    public PedidoResponse procesarCheckout() {
        Usuario usuario = getUsuarioAutenticado();

        List<Carrito> itemsCarrito = carritoRepository.findByUsuarioId(usuario.getId());
        if (itemsCarrito.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEstadoPago("COMPLETADO");

        double totalPedido = 0.0;

        for (Carrito item : itemsCarrito) {
            Producto producto = item.getProducto();

            if (producto.getStock() < item.getCantidad()) {
                throw new RuntimeException("No hay suficiente stock de: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());

            pedido.getDetalles().add(detalle);
            totalPedido += (producto.getPrecio() * item.getCantidad());
        }

        pedido.setTotal(totalPedido);

        Pedido guardado = pedidoRepository.save(pedido);

        carritoRepository.deleteAll(itemsCarrito);

        return Mapper.toPedidoResponse(guardado);
    }
}