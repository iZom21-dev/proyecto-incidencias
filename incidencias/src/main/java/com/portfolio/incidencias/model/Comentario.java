package com.portfolio.incidencias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usamos TEXT porque los comentarios pueden ser largos
    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    // Solo necesitamos saber cuándo se creó, no cuándo se actualiza (no permitiremos editar comentarios)
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    /* --- RELACIONES (FOREIGN KEYS) --- */

    // Muchos comentarios pertenecen a una única Incidencia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidencia_id", nullable = false)
    private Incidencia incidencia;

    // Muchos comentarios son escritos por un único Autor (Usuario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;
}