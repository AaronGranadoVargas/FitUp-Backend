package tfg.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioProfileResponse {
    private String nombre;
    private String email;
    private Double peso;
    private Integer altura;
}