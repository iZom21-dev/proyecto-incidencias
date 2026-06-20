package com.portfolio.incidencias.dto;

import com.portfolio.incidencias.model.enums.Rol;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDTO {
    // Fíjate que aquí NO existe el campo password.
    // Es imposible que se filtre por error.
    private Long id;
    private String username;
    private String email;
    private Rol rol;
}