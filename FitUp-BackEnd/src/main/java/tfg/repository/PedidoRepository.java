package tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tfg.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
