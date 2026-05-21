package tfg.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ejercicios_realizados")
public class EjercicioRealizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entrenamiento_id", nullable = false)
    private Entrenamiento entrenamiento;

    private String nombreEjercicio;
    private Integer series;
    private Integer repeticiones;
    private Double pesoKg;
}