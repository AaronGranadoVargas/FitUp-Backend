package tfg.dto.carrito;

import lombok.Data;

@Data
public class CarritoRequest {
    private Long productoId;
    private Integer cantidad;
}