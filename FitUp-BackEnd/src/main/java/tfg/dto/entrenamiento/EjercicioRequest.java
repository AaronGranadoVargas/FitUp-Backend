package tfg.dto.entrenamiento;

import lombok.Data;

@Data
public class EjercicioRequest {
    private String nombreEjercicio;
    private Integer series;
    private Integer repeticiones;
    private Double pesoKg;
}