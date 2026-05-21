package tfg.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import tfg.dto.usuario.UsuarioResponse;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private UsuarioResponse usuario;
}