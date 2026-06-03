package com.portfolio.incidencias.controller;

import com.portfolio.incidencias.model.Usuario;
import com.portfolio.incidencias.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    // Spring inyectará automáticamente este componente gracias a @RequiredArgsConstructor
    private final UsuarioRepository usuarioRepository;

    // Endpoint 1: Prueba de vida del servidor
    @GetMapping("/api/test")
    public String comprobarEstado() {
        return "¡El backend moderno está vivo y listo para picar código!";
    }

    // Endpoint 2: Prueba real de inserción y lectura en PostgreSQL
    @GetMapping("/api/test/db")
    public String probarBaseDeDatos() {
        // 1. Creamos un objeto entidad con datos ficticios
        Usuario nuevoUser = new Usuario();
        nuevoUser.setUsername("Zomahkiin_Test");
        nuevoUser.setEmail("test@portfolio.com");

        // 2. Lo guardamos en PostgreSQL con una sola línea (adiós a los INSERT INTO a mano)
        usuarioRepository.save(nuevoUser);

        // 3. Contamos cuántos registros hay en la tabla para validar la persistencia
        long total = usuarioRepository.count();

        return "¡Conexión exitosa! El usuario ha sido guardado en Docker Postgres. Total en DB: " + total;
    }
}