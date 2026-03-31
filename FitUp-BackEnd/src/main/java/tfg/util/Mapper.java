package tfg.util;

import tfg.model.*;
import tfg.dto.producto.ProductoResponse;
import tfg.dto.usuario.UsuarioResponse;
import tfg.dto.carrito.CarritoResponse;
import tfg.dto.entrenamiento.EntrenamientoResponse;
import tfg.dto.pedido.PedidoResponse;

public class Mapper {

    public static ProductoResponse toProductoResponse(Producto producto) {
        if (producto == null) return null;

        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria(),
                producto.getImagenUrl()
        );
    }

    public static UsuarioResponse toUsuarioResponse(Usuario usuario) {
        if (usuario == null) return null;

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getPesoActual(),
                usuario.getAltura(),
                usuario.getFechaRegistro()
        );
    }

    public static CarritoResponse toCarritoResponse(Carrito carrito) {
        if (carrito == null) return null;

        Long usuarioId = (carrito.getUsuario() != null) ? carrito.getUsuario().getId() : null;
        Long productoId = (carrito.getProducto() != null) ? carrito.getProducto().getId() : null;

        return new CarritoResponse(
                carrito.getId(),
                usuarioId,
                productoId,
                carrito.getCantidad()
        );
    }

    public static EntrenamientoResponse toEntrenamientoResponse(Entrenamiento entrenamiento) {
        if (entrenamiento == null) return null;

        Long usuarioId = (entrenamiento.getUsuario() != null) ? entrenamiento.getUsuario().getId() : null;

        return new EntrenamientoResponse(
                entrenamiento.getId(),
                usuarioId,
                entrenamiento.getFecha(),
                entrenamiento.getNotas()
        );
    }

    public static PedidoResponse toPedidoResponse(Pedido pedido) {
        if (pedido == null) return null;

        Long usuarioId = (pedido.getUsuario() != null) ? pedido.getUsuario().getId() : null;

        return new PedidoResponse(
                pedido.getId(),
                usuarioId,
                pedido.getFechaPedido(),
                pedido.getTotal(),
                pedido.getEstadoPago()
        );
    }
}