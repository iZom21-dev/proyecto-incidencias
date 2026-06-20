package com.portfolio.incidencias.service;

import com.portfolio.incidencias.dto.ComentarioRequestDTO;
import com.portfolio.incidencias.dto.ComentarioResponseDTO;
import com.portfolio.incidencias.dto.UsuarioResponseDTO;
import com.portfolio.incidencias.model.Comentario;
import com.portfolio.incidencias.model.Incidencia;
import com.portfolio.incidencias.model.Usuario;
import com.portfolio.incidencias.repository.ComentarioRepository;
import com.portfolio.incidencias.repository.IncidenciaRepository;
import com.portfolio.incidencias.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final IncidenciaRepository incidenciaRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioResponseDTO crearComentario(Long incidenciaId, ComentarioRequestDTO requestDTO) {
        Incidencia incidencia = incidenciaRepository.findById(incidenciaId)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con ID: " + incidenciaId));

        Usuario autor = usuarioRepository.findById(requestDTO.getAutorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + requestDTO.getAutorId()));

        Comentario comentario = Comentario.builder()
                .texto(requestDTO.getTexto())
                .incidencia(incidencia)
                .autor(autor)
                .build();

        Comentario comentarioGuardado = comentarioRepository.save(comentario);

        return mapearAComentarioDTO(comentarioGuardado);
    }

    // --- NUEVO MÉTODO PARA EL GET (LEER COMENTARIOS) ---
    public List<ComentarioResponseDTO> obtenerComentariosPorIncidencia(Long incidenciaId) {
        // 1. Comprobamos que la incidencia existe
        if (!incidenciaRepository.existsById(incidenciaId)) {
            throw new RuntimeException("Incidencia no encontrada con ID: " + incidenciaId);
        }

        // 2. Buscamos la lista de comentarios usando el nuevo método del repositorio
        List<Comentario> comentarios = comentarioRepository.findByIncidenciaId(incidenciaId);

        // 3. Traducimos la lista de Entidades a una lista de DTOs seguros
        return comentarios.stream()
                .map(this::mapearAComentarioDTO)
                .collect(Collectors.toList());
    }

    // Traductor (Mapper) manual
    private ComentarioResponseDTO mapearAComentarioDTO(Comentario comentario) {
        UsuarioResponseDTO autorDTO = UsuarioResponseDTO.builder()
                .id(comentario.getAutor().getId())
                .username(comentario.getAutor().getUsername())
                .email(comentario.getAutor().getEmail())
                .rol(comentario.getAutor().getRol())
                .build();

        return ComentarioResponseDTO.builder()
                .id(comentario.getId())
                .texto(comentario.getTexto())
                .fechaCreacion(comentario.getFechaCreacion())
                .autor(autorDTO)
                .build();
    }
}