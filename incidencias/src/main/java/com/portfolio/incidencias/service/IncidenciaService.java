package com.portfolio.incidencias.service;

import com.portfolio.incidencias.model.Incidencia;
import com.portfolio.incidencias.repository.IncidenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    // Inyectamos el Repositorio (nuestro DAO moderno)
    // Usamos final + @RequiredArgsConstructor para una inyección limpia sin @Autowired
    private final IncidenciaRepository incidenciaRepository;

    /**
     * Obtiene todas las incidencias de la base de datos.
     * En el futuro, aquí añadiremos lógica de paginación y seguridad.
     */
    public List<Incidencia> obtenerTodasLasIncidencias() {
        // Fíjate qué limpio es. El repositorio ya sabe cómo hacer el "SELECT * FROM incidencias"
        return incidenciaRepository.findAll();
    }
}