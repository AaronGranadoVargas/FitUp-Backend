package tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tfg.dto.entrenamiento.EntrenamientoRequest;
import tfg.dto.entrenamiento.EntrenamientoResponse;
import tfg.model.Entrenamiento;
import tfg.model.Usuario;
import tfg.repository.EntrenamientoRepository;
import tfg.repository.UsuarioRepository;
import tfg.util.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntrenamientoService {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<EntrenamientoResponse> obtenerPorUsuario(Long usuarioId) {
        return entrenamientoRepository.findAll().stream()
                .filter(e -> e.getUsuario() != null && e.getUsuario().getId().equals(usuarioId))
                .map(Mapper::toEntrenamientoResponse)
                .collect(Collectors.toList());
    }

    public EntrenamientoResponse crearEntrenamiento(EntrenamientoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow();

        Entrenamiento entrenamiento = new Entrenamiento();
        entrenamiento.setUsuario(usuario);
        entrenamiento.setFecha(request.getFecha());
        entrenamiento.setNotas(request.getNotas());

        Entrenamiento guardado = entrenamientoRepository.save(entrenamiento);
        return Mapper.toEntrenamientoResponse(guardado);
    }
}