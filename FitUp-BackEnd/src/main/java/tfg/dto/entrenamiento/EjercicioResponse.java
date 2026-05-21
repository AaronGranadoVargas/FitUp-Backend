package tfg.dto.entrenamiento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EjercicioResponse {
    private Long id;
    private String nombreEjercicio;
    private Integer series;
    private Integer repeticiones;
    private Double pesoKg;
}