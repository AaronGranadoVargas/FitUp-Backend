package tfg.dto.usuario;

import lombok.Data;

@Data
public class UsuarioUpdateRequest {
    private String nombre;
    private Double peso;
    private Integer altura;
}