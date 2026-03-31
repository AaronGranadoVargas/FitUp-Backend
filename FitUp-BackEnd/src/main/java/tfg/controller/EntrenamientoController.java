package tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tfg.service.EntrenamientoService;

@RestController
@RequestMapping("/entrenamientos")
public class EntrenamientoController {
    @Autowired
    private EntrenamientoService entrenamientoService;
}
