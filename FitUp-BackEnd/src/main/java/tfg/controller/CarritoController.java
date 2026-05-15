package tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.dto.carrito.CarritoRequest;
import tfg.dto.carrito.CarritoResponse;
import tfg.service.CarritoService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") // IMPRESCINDIBLE PARA REACT NATIVE
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<CarritoResponse>> obtenerCarrito() {
        return ResponseEntity.ok(carritoService.obtenerCarritoDeUsuario());
    }

    @PostMapping
    public ResponseEntity<CarritoResponse> agregar(@RequestBody CarritoRequest request) {
        return ResponseEntity.ok(carritoService.agregarAlCarrito(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long id) {
        carritoService.eliminarDelCarrito(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/vaciar")
    public ResponseEntity<Void> vaciarCarrito() {
        carritoService.vaciarCarritoDeUsuario();
        return ResponseEntity.noContent().build();
    }
}