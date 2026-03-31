package tfg.dto.entrenamiento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrenamientoRequest {
    private Long usuarioId;
    private LocalDate fecha;
    private String notas;
}