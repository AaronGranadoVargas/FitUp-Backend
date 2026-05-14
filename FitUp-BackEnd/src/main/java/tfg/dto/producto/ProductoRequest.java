package tfg.dto.producto;

import lombok.Data;
import tfg.enums.Categoria; // Importa tu Enum

@Data
public class ProductoRequest {
    private String nombre;
    private Double precio;
    private Integer stock;
    private String descripcion;
    private Categoria categoria; // Cambiado de String a Categoria
    private String imagenUrl;
}