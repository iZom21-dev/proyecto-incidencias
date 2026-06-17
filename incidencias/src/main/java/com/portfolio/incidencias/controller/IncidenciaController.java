package com.portfolio.incidencias.controller;

import com.portfolio.incidencias.model.Incidencia;
import com.portfolio.incidencias.service.IncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// @RequestMapping define la ruta base para todos los endpoints de este controlador
@RequestMapping("/api/incidencias")
@RequiredArgsConstructor
public class IncidenciaController {

    // El Controller es "tonto", así que le inyectamos el Service para que haga el trabajo real
    private final IncidenciaService incidenciaService;

    /**
     * Endpoint para obtener el listado de todas las incidencias.
     * Al devolver ResponseEntity, podemos controlar el código de estado HTTP (200 OK, 404, etc.)
     */
    @GetMapping
    public ResponseEntity<List<Incidencia>> listarIncidencias() {
        List<Incidencia> incidencias = incidenciaService.obtenerTodasLasIncidencias();

        // Devolvemos un HTTP 200 (OK) con la lista de incidencias en el "body" (que se convertirá en JSON)
        return ResponseEntity.ok(incidencias);
    }
}