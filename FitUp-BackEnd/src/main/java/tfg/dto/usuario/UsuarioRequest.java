package tfg.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
    private String nombre;
    private String email;
    private String password;
    private Double pesoActual;
    private Integer altura;
}