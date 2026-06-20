package com.portfolio.incidencias.dto;

import com.portfolio.incidencias.model.enums.Estado;
import lombok.Data;

@Data
public class IncidenciaUpdateDTO {
    // Permite actualizar el estado (ej. pasar a EN_PROGRESO o RESUELTO)
    private Estado estado;

    // Permite asignar un técnico al ticket
    private Long tecnicoId;
}