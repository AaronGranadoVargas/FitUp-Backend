package tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tfg.dto.usuario.UsuarioProfileResponse;
import tfg.dto.usuario.UsuarioRequest;
import tfg.dto.usuario.UsuarioResponse;
import tfg.dto.usuario.UsuarioUpdateRequest;
import tfg.enums.Rol;
import tfg.model.Usuario;
import tfg.repository.UsuarioRepository;
import tfg.util.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResponse> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(Mapper::toUsuarioResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setRol(Rol.USER);
        usuario.setPesoActual(request.getPesoActual());
        usuario.setAltura(request.getAltura());
        usuario.setFechaRegistro(LocalDateTime.now());

        Usuario guardado = usuarioRepository.save(usuario);
        return Mapper.toUsuarioResponse(guardado);
    }

    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPesoActual(request.getPesoActual());
        usuario.setAltura(request.getAltura());

        Usuario actualizado = usuarioRepository.save(usuario);
        return Mapper.toUsuarioResponse(actualizado);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


    public UsuarioProfileResponse obtenerPerfil() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new UsuarioProfileResponse(
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPesoActual(),
                usuario.getAltura(),
                usuario.getRol()
        );
    }

    public UsuarioProfileResponse actualizarPerfil(UsuarioUpdateRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(request.getNombre());
        usuario.setPesoActual(request.getPeso());
        usuario.setAltura(request.getAltura());

        Usuario guardado = usuarioRepository.save(usuario);

        return new UsuarioProfileResponse(
                guardado.getNombre(),
                guardado.getEmail(),
                guardado.getPesoActual(),
                guardado.getAltura(),
                guardado.getRol()
        );
    }
}