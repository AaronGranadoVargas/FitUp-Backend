package tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tfg.model.Entrenamiento;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {

    List<Entrenamiento> findByUsuarioId(Long usuarioId);

    Optional<Entrenamiento> findByUsuarioIdAndFecha(Long usuarioId, LocalDate fecha);
}