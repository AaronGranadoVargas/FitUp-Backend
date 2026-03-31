package tfg.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ejercicios_realizados")
@Getter
@Setter
public class EjercicioRealizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEjercicio;
    private Integer series;
    private Integer repeticiones;
    private Double pesoKg;

    @ManyToOne
    @JoinColumn(name = "entrenamiento_id")
    private Entrenamiento entrenamiento;
}
