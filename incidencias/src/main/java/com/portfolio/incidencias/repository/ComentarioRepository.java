package com.portfolio.incidencias.repository;

import com.portfolio.incidencias.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    // Magia de Spring Data: Genera la query SELECT * FROM comentarios WHERE incidencia_id = ?
    List<Comentario> findByIncidenciaId(Long incidenciaId);
}