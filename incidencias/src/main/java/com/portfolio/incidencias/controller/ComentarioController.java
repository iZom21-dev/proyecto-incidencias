package com.portfolio.incidencias.controller;

import com.portfolio.incidencias.dto.ComentarioRequestDTO;
import com.portfolio.incidencias.dto.ComentarioResponseDTO;
import com.portfolio.incidencias.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Mapeamos la ruta base para que cuelgue directamente de una incidencia específica
@RequestMapping("/api/incidencias")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    /**
     * Endpoint para obtener todos los comentarios de una incidencia.
     * La URL será del tipo: GET /api/incidencias/1/comentarios
     */
    @GetMapping("/{incidenciaId}/comentarios")
    public ResponseEntity<List<ComentarioResponseDTO>> obtenerComentarios(
            @PathVariable Long incidenciaId) {

        List<ComentarioResponseDTO> comentarios = comentarioService.obtenerComentariosPorIncidencia(incidenciaId);
        return ResponseEntity.ok(comentarios);
    }

    /**
     * Endpoint para añadir un comentario a una incidencia existente.
     * La URL será del tipo: POST /api/incidencias/1/comentarios
     */
    @PostMapping("/{incidenciaId}/comentarios")
    public ResponseEntity<ComentarioResponseDTO> crearComentario(
            @PathVariable Long incidenciaId,
            @RequestBody ComentarioRequestDTO requestDTO) {

        ComentarioResponseDTO nuevoComentario = comentarioService.crearComentario(incidenciaId, requestDTO);

        // Devolvemos 201 (Created) indicando que el comentario se guardó con éxito
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }
}