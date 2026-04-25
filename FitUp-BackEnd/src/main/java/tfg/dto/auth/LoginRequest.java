package tfg.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "El email es obligatorio para iniciar sesión")
    @Email(message = "El formato del correo no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria para iniciar sesión")
    private String password;
}