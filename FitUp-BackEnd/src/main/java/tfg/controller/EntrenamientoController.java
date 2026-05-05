package tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.dto.entrenamiento.EntrenamientoRequest;
import tfg.dto.entrenamiento.EntrenamientoResponse;
import tfg.service.EntrenamientoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/entrenamientos")
public class EntrenamientoController {

    @Autowired
    private EntrenamientoService entrenamientoService;

    @GetMapping("/mis-entrenamientos")
    public ResponseEntity<List<EntrenamientoResponse>> obtenerMisEntrenamientos() {
        return ResponseEntity.ok(entrenamientoService.obtenerMisEntrenamientos());
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<EntrenamientoResponse> obtenerPorFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(entrenamientoService.obtenerPorFecha(fecha));
    }

    @PostMapping
    public ResponseEntity<EntrenamientoResponse> guardarOActualizar(@RequestBody EntrenamientoRequest request) {
        return ResponseEntity.ok(entrenamientoService.guardarOActualizar(request));
    }
}