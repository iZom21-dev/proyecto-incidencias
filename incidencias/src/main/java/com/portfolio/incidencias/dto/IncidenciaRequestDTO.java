package com.portfolio.incidencias.dto;

import com.portfolio.incidencias.model.enums.Prioridad;
import lombok.Data;

@Data
public class IncidenciaRequestDTO {
    private String titulo;
    private String descripcion;
    private Prioridad prioridad;

    // Solo necesitamos el ID del usuario que está creando el ticket
    private Long creadorId;
}