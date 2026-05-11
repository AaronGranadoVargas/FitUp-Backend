package tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tfg.model.Carrito;
import java.util.List;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    List<Carrito> findByUsuarioId(Long usuarioId);
    Optional<Carrito> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId);
}