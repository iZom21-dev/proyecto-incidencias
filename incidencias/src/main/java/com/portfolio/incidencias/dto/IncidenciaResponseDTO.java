package com.portfolio.incidencias.dto;

import com.portfolio.incidencias.model.enums.Estado;
import com.portfolio.incidencias.model.enums.Prioridad;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class IncidenciaResponseDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private Estado estado;
    private Prioridad prioridad;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    // Usamos nuestros DTOs seguros en lugar de las Entidades de base de datos
    private UsuarioResponseDTO creador;
    private UsuarioResponseDTO tecnico;
}