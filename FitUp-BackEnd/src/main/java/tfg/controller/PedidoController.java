package tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.dto.pedido.PedidoResponse;
import tfg.service.PedidoService;
import tfg.service.PaypalService;

import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired private PedidoService pedidoService;
    @Autowired private PaypalService paypalService;

    @PostMapping("/paypal/create")
    public ResponseEntity<Map<String, String>> crearPagoPaypal(@RequestBody Map<String, Object> payload) {
        Double total = Double.valueOf(payload.get("total").toString());
        String returnUrl = payload.get("returnUrl").toString();
        String cancelUrl = payload.get("cancelUrl").toString();

        String url = paypalService.crearPedidoPaypal(total, returnUrl, cancelUrl);
        return ResponseEntity.ok(Collections.singletonMap("url", url));
    }

    @PostMapping("/paypal/capture")
    public ResponseEntity<PedidoResponse> capturarPagoPaypal(@RequestParam String orderId) {
        boolean pagado = paypalService.capturarPago(orderId);
        if(pagado) {
            return ResponseEntity.ok(pedidoService.procesarCheckout());
        }
        throw new RuntimeException("Fallo al verificar el pago en PayPal");
    }
}