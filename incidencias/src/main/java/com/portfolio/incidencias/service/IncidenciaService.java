package com.portfolio.incidencias.service;

import com.portfolio.incidencias.dto.IncidenciaResponseDTO;
import com.portfolio.incidencias.dto.IncidenciaRequestDTO;
import com.portfolio.incidencias.dto.IncidenciaUpdateDTO;
import com.portfolio.incidencias.dto.UsuarioResponseDTO;
import com.portfolio.incidencias.model.Incidencia;
import com.portfolio.incidencias.model.Usuario;
import com.portfolio.incidencias.model.enums.Estado;
import com.portfolio.incidencias.repository.IncidenciaRepository;
import com.portfolio.incidencias.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final UsuarioRepository usuarioRepository;

    public List<IncidenciaResponseDTO> obtenerTodasLasIncidencias() {
        List<Incidencia> incidencias = incidenciaRepository.findAll();

        return incidencias.stream()
                .map(this::mapearAIncidenciaDTO)
                .collect(Collectors.toList());
    }

    // --- NUEVO MÉTODO PARA EL GET POR ID ---
    public IncidenciaResponseDTO obtenerIncidenciaPorId(Long id) {
        Incidencia incidencia = incidenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con ID: " + id));

        return mapearAIncidenciaDTO(incidencia);
    }

    public IncidenciaResponseDTO crearIncidencia(IncidenciaRequestDTO requestDTO) {
        Usuario creador = usuarioRepository.findById(requestDTO.getCreadorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + requestDTO.getCreadorId()));

        Incidencia nuevaIncidencia = Incidencia.builder()
                .titulo(requestDTO.getTitulo())
                .descripcion(requestDTO.getDescripcion())
                .estado(Estado.ABIERTO)
                .prioridad(requestDTO.getPrioridad())
                .creador(creador)
                .build();

        Incidencia incidenciaGuardada = incidenciaRepository.save(nuevaIncidencia);

        return mapearAIncidenciaDTO(incidenciaGuardada);
    }

    public IncidenciaResponseDTO actualizarIncidencia(Long id, IncidenciaUpdateDTO updateDTO) {
        Incidencia incidencia = incidenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con ID: " + id));

        if (updateDTO.getEstado() != null) {
            incidencia.setEstado(updateDTO.getEstado());
        }

        if (updateDTO.getTecnicoId() != null) {
            Usuario tecnico = usuarioRepository.findById(updateDTO.getTecnicoId())
                    .orElseThrow(() -> new RuntimeException("Técnico no encontrado con ID: " + updateDTO.getTecnicoId()));
            incidencia.setTecnico(tecnico);
        }

        Incidencia actualizada = incidenciaRepository.save(incidencia);

        return mapearAIncidenciaDTO(actualizada);
    }

    // Traductor (Mapper) manual
    private IncidenciaResponseDTO mapearAIncidenciaDTO(Incidencia incidencia) {
        UsuarioResponseDTO creadorDTO = UsuarioResponseDTO.builder()
                .id(incidencia.getCreador().getId())
                .username(incidencia.getCreador().getUsername())
                .email(incidencia.getCreador().getEmail())
                .rol(incidencia.getCreador().getRol())
                .build();

        UsuarioResponseDTO tecnicoDTO = null;
        if (incidencia.getTecnico() != null) {
            tecnicoDTO = UsuarioResponseDTO.builder()
                    .id(incidencia.getTecnico().getId())
                    .username(incidencia.getTecnico().getUsername())
                    .email(incidencia.getTecnico().getEmail())
                    .rol(incidencia.getTecnico().getRol())
                    .build();
        }

        return IncidenciaResponseDTO.builder()
                .id(incidencia.getId())
                .titulo(incidencia.getTitulo())
                .descripcion(incidencia.getDescripcion())
                .estado(incidencia.getEstado())
                .prioridad(incidencia.getPrioridad())
                .fechaCreacion(incidencia.getFechaCreacion())
                .fechaActualizacion(incidencia.getFechaActualizacion())
                .creador(creadorDTO)
                .tecnico(tecnicoDTO)
                .build();
    }
}