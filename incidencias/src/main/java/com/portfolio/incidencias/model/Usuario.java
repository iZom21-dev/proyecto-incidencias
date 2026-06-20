package com.portfolio.incidencias.model;

import com.portfolio.incidencias.model.enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: Genera getters, setters, toString, equals y hashCode
@Builder // Lombok: Permite crear objetos con el patrón Builder (muy útil para testing)
@NoArgsConstructor // Lombok: Constructor vacío obligatorio para JPA
@AllArgsConstructor // Lombok: Constructor con todos los campos (lo usa el Builder)
@Entity // JPA: Indica que esta clase es una tabla de base de datos
@Table(name = "usuarios") // JPA: Nombra la tabla explícitamente en plural

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental en PostgreSQL (SERIAL)
    private Long id;

    // unique = true crea un índice de unicidad en BD automáticamente
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // CRÍTICO: Si no ponemos EnumType.STRING, JPA guardará 0, 1 o 2 (el orden del Enum)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;
}