package tfg.dto.entrenamiento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrenamientoRequest {
    private LocalDate fecha;
    private String notas;
    private List<EjercicioRequest> ejercicios;
}