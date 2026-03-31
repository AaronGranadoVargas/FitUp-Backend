package tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tfg.repository.EntrenamientoRepository;

@Service
public class EntrenamientoService {
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;
}
