package tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tfg.dto.pedido.PedidoRequest;
import tfg.dto.pedido.PedidoResponse;
import tfg.model.Pedido;
import tfg.model.Usuario;
import tfg.repository.PedidoRepository;
import tfg.repository.UsuarioRepository;
import tfg.util.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PedidoResponse> obtenerPorUsuario(Long usuarioId) {
        return pedidoRepository.findAll().stream()
                .filter(p -> p.getUsuario() != null && p.getUsuario().getId().equals(usuarioId))
                .map(Mapper::toPedidoResponse)
                .collect(Collectors.toList());
    }

    public PedidoResponse crearPedido(PedidoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setTotal(request.getTotal());
        pedido.setEstadoPago(request.getEstadoPago() != null ? request.getEstadoPago() : "PENDIENTE");

        Pedido guardado = pedidoRepository.save(pedido);
        return Mapper.toPedidoResponse(guardado);
    }
}