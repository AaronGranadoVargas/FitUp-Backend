package tfg.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tfg.enums.Categoria;

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String imagenUrl;
}