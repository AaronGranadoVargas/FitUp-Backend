package tfg.dto.carrito;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoResponse {
    private Long id;
    private Long productoId;
    private String nombreProducto;
    private Double precioUnitario;
    private Integer cantidad;
    private Double subtotal;
}