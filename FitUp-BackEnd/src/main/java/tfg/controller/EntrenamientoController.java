package tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.dto.entrenamiento.EntrenamientoRequest;
import tfg.dto.entrenamiento.EntrenamientoResponse;
import tfg.service.EntrenamientoService;

import java.util.List;

@RestController
@RequestMapping("/api/entrenamientos")
public class EntrenamientoController {

    @Autowired
    private EntrenamientoService entrenamientoService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<EntrenamientoResponse>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(entrenamientoService.obtenerPorUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<EntrenamientoResponse> crear(@RequestBody EntrenamientoRequest request) {
        return ResponseEntity.ok(entrenamientoService.crearEntrenamiento(request));
    }
}