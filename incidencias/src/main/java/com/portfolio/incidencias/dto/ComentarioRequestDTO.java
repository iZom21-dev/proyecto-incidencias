package com.portfolio.incidencias.dto;

import lombok.Data;

@Data
public class ComentarioRequestDTO {
    // Solo necesitamos el texto y quién lo escribe.
    // El ID de la incidencia vendrá por la URL (/api/incidencias/{id}/comentarios)
    private String texto;
    private Long autorId;
}