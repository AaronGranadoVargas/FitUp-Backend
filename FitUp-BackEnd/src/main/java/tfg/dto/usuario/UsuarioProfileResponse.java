package tfg.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.enums.Rol;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioProfileResponse {
    private String nombre;
    private String email;
    private Double pesoActual;
    private Integer altura;
    // 👇 AÑADE ESTO 👇
    private Rol rol;
}