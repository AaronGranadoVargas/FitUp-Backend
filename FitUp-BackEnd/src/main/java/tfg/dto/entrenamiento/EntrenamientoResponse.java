package tfg.dto.entrenamiento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrenamientoResponse {
    private Long id;
    private Long usuarioId;
    private LocalDate fecha;
    private String notas;
    private List<EjercicioResponse> ejercicios;
}