package tfg.dto.carrito;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoRequest {
    private Long usuarioId;
    private Long productoId;
    private Integer cantidad;
}