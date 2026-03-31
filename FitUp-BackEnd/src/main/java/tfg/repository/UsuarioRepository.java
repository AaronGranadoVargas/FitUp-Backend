package tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tfg.model.Producto;
import tfg.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
