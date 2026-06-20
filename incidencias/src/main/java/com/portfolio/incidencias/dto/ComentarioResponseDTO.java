package com.portfolio.incidencias.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ComentarioResponseDTO {
    private Long id;
    private String texto;
    private LocalDateTime fechaCreacion;

    // Devolvemos los datos seguros del autor (sin contraseñas)
    private UsuarioResponseDTO autor;
}