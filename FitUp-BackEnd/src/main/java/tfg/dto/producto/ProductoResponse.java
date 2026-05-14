package tfg.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.enums.Categoria;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private String descripcion;
    private Categoria categoria;
    private String imagenUrl;
}