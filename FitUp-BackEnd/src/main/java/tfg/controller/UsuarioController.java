package tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.dto.usuario.UsuarioProfileResponse;
import tfg.dto.usuario.UsuarioRequest;
import tfg.dto.usuario.UsuarioResponse;
import tfg.dto.usuario.UsuarioUpdateRequest;
import tfg.service.UsuarioService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(@RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.crearUsuario(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioProfileResponse> obtenerPerfil() {
        return ResponseEntity.ok(usuarioService.obtenerPerfil());
    }

    @PutMapping("/perfil")
    public ResponseEntity<UsuarioProfileResponse> actualizarPerfil(@RequestBody UsuarioUpdateRequest request) {
        return ResponseEntity.ok(usuarioService.actualizarPerfil(request));
    }
}