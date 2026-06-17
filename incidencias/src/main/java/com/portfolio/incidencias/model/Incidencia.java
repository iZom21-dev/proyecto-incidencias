package com.portfolio.incidencias.model;

import com.portfolio.incidencias.model.enums.Estado;
import com.portfolio.incidencias.model.enums.Prioridad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incidencias")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    // Con TEXT en lugar de VARCHAR, permitimos descripciones muy largas
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridad prioridad;

    // MAGIA: Hibernate rellenará esto solo cuando se haga el primer INSERT
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    // MAGIA: Hibernate actualizará esto solo cada vez que se haga un UPDATE
    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;

    /* --- RELACIONES (FOREIGN KEYS) --- */

    // Muchos tickets pueden pertenecer a un mismo creador (Cliente)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creador_id", nullable = false)
    private Usuario creador;

    // Muchos tickets pueden estar asignados a un mismo técnico
    // nullable = true (por defecto) porque al abrir un ticket nadie lo tiene asignado aún
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;
}