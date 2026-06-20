package com.portfolio.incidencias.controller;

import com.portfolio.incidencias.dto.IncidenciaRequestDTO;
import com.portfolio.incidencias.dto.IncidenciaResponseDTO;
import com.portfolio.incidencias.dto.IncidenciaUpdateDTO;
import com.portfolio.incidencias.service.IncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
@RequiredArgsConstructor
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    @GetMapping
    public ResponseEntity<List<IncidenciaResponseDTO>> listarIncidencias() {
        List<IncidenciaResponseDTO> incidencias = incidenciaService.obtenerTodasLasIncidencias();
        return ResponseEntity.ok(incidencias);
    }

    /**
     * Endpoint para obtener una única incidencia por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaResponseDTO> obtenerIncidenciaPorId(@PathVariable Long id) {
        IncidenciaResponseDTO incidencia = incidenciaService.obtenerIncidenciaPorId(id);
        return ResponseEntity.ok(incidencia);
    }

    @PostMapping
    public ResponseEntity<IncidenciaResponseDTO> crearIncidencia(@RequestBody IncidenciaRequestDTO requestDTO) {
        IncidenciaResponseDTO nuevaIncidencia = incidenciaService.crearIncidencia(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaIncidencia);
    }

    /**
     * Endpoint para actualizar una incidencia (Asignar técnico y/o cambiar estado)
     * @PathVariable coge el {id} de la URL (ej: /api/incidencias/1)
     */
    @PutMapping("/{id}")
    public ResponseEntity<IncidenciaResponseDTO> actualizarIncidencia(
            @PathVariable Long id,
            @RequestBody IncidenciaUpdateDTO updateDTO) {

        IncidenciaResponseDTO incidenciaActualizada = incidenciaService.actualizarIncidencia(id, updateDTO);
        return ResponseEntity.ok(incidenciaActualizada);
    }
}