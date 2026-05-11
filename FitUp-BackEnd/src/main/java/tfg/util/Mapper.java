package tfg.util;

import tfg.model.*;
import tfg.dto.producto.ProductoResponse;
import tfg.dto.usuario.UsuarioResponse;
import tfg.dto.carrito.CarritoResponse;
import tfg.dto.entrenamiento.EntrenamientoResponse;
import tfg.dto.entrenamiento.EjercicioResponse; // Añadido
import tfg.dto.pedido.PedidoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        Producto p = carrito.getProducto();
        Double subtotal = (p != null && p.getPrecio() != null && carrito.getCantidad() != null)
                ? p.getPrecio() * carrito.getCantidad()
                : 0.0;

        return new CarritoResponse(
                carrito.getId(),
                p != null ? p.getId() : null,
                p != null ? p.getNombre() : "Producto desconocido",
                p != null ? p.getPrecio() : 0.0,
                carrito.getCantidad(),
                subtotal
        );
    }

    public static EntrenamientoResponse toEntrenamientoResponse(Entrenamiento e) {
        if (e == null) return null;

        List<EjercicioResponse> ejerciciosDTO = new ArrayList<>();
        if (e.getEjercicios() != null) {
            ejerciciosDTO = e.getEjercicios().stream()
                    .map(ej -> new EjercicioResponse(ej.getId(), ej.getNombreEjercicio(), ej.getSeries(), ej.getRepeticiones(), ej.getPesoKg()))
                    .collect(Collectors.toList());
        }

        return new EntrenamientoResponse(
                e.getId(),
                (e.getUsuario() != null) ? e.getUsuario().getId() : null,
                e.getFecha(),
                e.getNotas() != null ? e.getNotas() : "",
                ejerciciosDTO
        );
    }

    public static EjercicioResponse toEjercicioResponse(EjercicioRealizado ej) {
        if (ej == null) return null;
        return new EjercicioResponse(
                ej.getId(),
                ej.getNombreEjercicio(),
                ej.getSeries(),
                ej.getRepeticiones(),
                ej.getPesoKg()
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