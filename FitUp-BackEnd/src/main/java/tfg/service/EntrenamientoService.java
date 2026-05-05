package tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tfg.dto.entrenamiento.EntrenamientoRequest;
import tfg.dto.entrenamiento.EntrenamientoResponse;
import tfg.model.Entrenamiento;
import tfg.model.Usuario;
import tfg.repository.EntrenamientoRepository;
import tfg.repository.UsuarioRepository;
import tfg.util.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrenamientoService {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario getUsuarioAutenticado() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<EntrenamientoResponse> obtenerMisEntrenamientos() {
        Usuario usuario = getUsuarioAutenticado();
        return entrenamientoRepository.findByUsuarioId(usuario.getId()).stream()
                .map(Mapper::toEntrenamientoResponse)
                .collect(Collectors.toList());
    }

    public EntrenamientoResponse obtenerPorFecha(LocalDate fecha) {
        Usuario usuario = getUsuarioAutenticado();
        Optional<Entrenamiento> entrenamiento = entrenamientoRepository.findByUsuarioIdAndFecha(usuario.getId(), fecha);

        if (entrenamiento.isPresent()) {
            return Mapper.toEntrenamientoResponse(entrenamiento.get());
        }

        return new EntrenamientoResponse(null, usuario.getId(), fecha, "");
    }

    public EntrenamientoResponse guardarOActualizar(EntrenamientoRequest request) {
        Usuario usuario = getUsuarioAutenticado();

        Entrenamiento entrenamiento = entrenamientoRepository.findByUsuarioIdAndFecha(usuario.getId(), request.getFecha())
                .orElse(new Entrenamiento());

        entrenamiento.setUsuario(usuario);
        entrenamiento.setFecha(request.getFecha());
        entrenamiento.setNotas(request.getNotas());

        Entrenamiento guardado = entrenamientoRepository.save(entrenamiento);
        return Mapper.toEntrenamientoResponse(guardado);
    }
}