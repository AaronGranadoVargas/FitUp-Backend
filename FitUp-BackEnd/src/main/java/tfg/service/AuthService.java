package tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tfg.dto.auth.AuthResponse;
import tfg.dto.auth.LoginRequest;
import tfg.dto.usuario.UsuarioRequest;
import tfg.enums.Rol;
import tfg.model.Usuario;
import tfg.repository.UsuarioRepository;
import tfg.security.JwtService;
import tfg.util.Mapper;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;

    public AuthResponse registrar(UsuarioRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Este email ya está en uso");
        }

        Usuario u = new Usuario();
        u.setNombre(request.getNombre());
        u.setEmail(request.getEmail());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        u.setRol(Rol.USER);
        u.setPesoActual(request.getPesoActual());
        u.setAltura(request.getAltura());
        u.setFechaRegistro(LocalDateTime.now());

        Usuario guardado = usuarioRepository.save(u);

        String token = jwtService.generarToken(guardado);
        return new AuthResponse(token, Mapper.toUsuarioResponse(guardado));
    }

    public AuthResponse login(LoginRequest request) {
        Usuario u = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(request.getPassword(), u.getPassword())) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }

        String token = jwtService.generarToken(u);
        return new AuthResponse(token, Mapper.toUsuarioResponse(u));
    }
}