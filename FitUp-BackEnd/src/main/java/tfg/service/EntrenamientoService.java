package tfg.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tfg.dto.entrenamiento.*;
import tfg.model.*;
import tfg.repository.*;
import tfg.util.Mapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntrenamientoService {
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario getUsuarioAutenticado() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByEmail(email).orElseThrow();
    }

    public List<EntrenamientoResponse> obtenerMisEntrenamientos() {
        Usuario usuario = getUsuarioAutenticado();
        return entrenamientoRepository.findByUsuarioId(usuario.getId()).stream()
                .map(Mapper::toEntrenamientoResponse)
                .collect(Collectors.toList());
    }

    public EntrenamientoResponse obtenerPorFecha(LocalDate fecha) {
        Usuario usuario = getUsuarioAutenticado();
        return entrenamientoRepository.findByUsuarioIdAndFecha(usuario.getId(), fecha)
                .map(Mapper::toEntrenamientoResponse)
                .orElse(new EntrenamientoResponse(null, usuario.getId(), fecha, "", new ArrayList<>()));
    }

    @Transactional
    public EntrenamientoResponse guardarOActualizar(EntrenamientoRequest request) {
        Usuario usuario = getUsuarioAutenticado();
        Entrenamiento entrenamiento = entrenamientoRepository.findByUsuarioIdAndFecha(usuario.getId(), request.getFecha())
                .orElse(new Entrenamiento());

        entrenamiento.setUsuario(usuario);
        entrenamiento.setFecha(request.getFecha());
        entrenamiento.setNotas(request.getNotas());

        if (entrenamiento.getEjercicios() != null) {
            entrenamiento.getEjercicios().clear();
        } else {
            entrenamiento.setEjercicios(new ArrayList<>());
        }

        if (request.getEjercicios() != null) {
            for (EjercicioRequest ejReq : request.getEjercicios()) {
                EjercicioRealizado nuevoEj = new EjercicioRealizado();
                nuevoEj.setNombreEjercicio(ejReq.getNombreEjercicio());
                nuevoEj.setSeries(ejReq.getSeries());
                nuevoEj.setRepeticiones(ejReq.getRepeticiones());
                nuevoEj.setPesoKg(ejReq.getPesoKg());
                nuevoEj.setEntrenamiento(entrenamiento);
                entrenamiento.getEjercicios().add(nuevoEj);
            }
        }
        return Mapper.toEntrenamientoResponse(entrenamientoRepository.save(entrenamiento));
    }
}