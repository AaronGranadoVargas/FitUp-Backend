package tfg.dto.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {
    private Long id;
    private Long usuarioId;
    private LocalDateTime fechaPedido;
    private Double total;
    private String estadoPago;
}