package tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tfg.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}
