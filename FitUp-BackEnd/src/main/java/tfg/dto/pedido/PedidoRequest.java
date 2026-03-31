package tfg.dto.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {
    private Long usuarioId;
    private Double total;
    private String estadoPago; // Ej: PENDIENTE, PAGADO
}