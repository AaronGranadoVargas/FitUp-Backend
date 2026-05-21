package tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tfg.model.Producto;
import tfg.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
