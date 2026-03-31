package tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tfg.model.Entrenamiento;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {
}
