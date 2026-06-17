package com.portfolio.incidencias.config;

import com.portfolio.incidencias.model.Incidencia;
import com.portfolio.incidencias.model.Usuario;
import com.portfolio.incidencias.model.enums.Estado;
import com.portfolio.incidencias.model.enums.Prioridad;
import com.portfolio.incidencias.model.enums.Rol;
import com.portfolio.incidencias.repository.IncidenciaRepository;
import com.portfolio.incidencias.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataSeedConfig {

    private final UsuarioRepository usuarioRepository;
    private final IncidenciaRepository incidenciaRepository;

    @Bean
    public CommandLineRunner poblarBaseDeDatos() {
        return args -> {
            // Solo insertamos datos si la base de datos está vacía
            if (usuarioRepository.count() == 0) {

                // 1. Crear y guardar un Usuario Cliente usando el patrón Builder
                Usuario cliente = Usuario.builder()
                        .username("cliente1")
                        .email("cliente1@empresa.com")
                        .password("1234") // Ojo, en prod esto iría hasheado con BCrypt
                        .rol(Rol.ROLE_CLIENTE)
                        .build();
                usuarioRepository.save(cliente);

                // 2. Crear y guardar un Usuario Técnico
                Usuario tecnico = Usuario.builder()
                        .username("tech_master")
                        .email("soporte@empresa.com")
                        .password("1234")
                        .rol(Rol.ROLE_TECNICO)
                        .build();
                usuarioRepository.save(tecnico);

                // 3. Crear una Incidencia abierta por el cliente, aún sin técnico asignado
                Incidencia incidencia1 = Incidencia.builder()
                        .titulo("Pantalla azul al iniciar")
                        .descripcion("Cada vez que abro el Excel me salta la pantalla azul de Windows.")
                        .estado(Estado.ABIERTO)
                        .prioridad(Prioridad.ALTA)
                        .creador(cliente)
                        .build();
                incidenciaRepository.save(incidencia1);

                // 4. Crear otra Incidencia ya asignada al técnico y en progreso
                Incidencia incidencia2 = Incidencia.builder()
                        .titulo("El ratón no funciona")
                        .descripcion("El puntero se ha quedado congelado, le he cambiado las pilas y nada.")
                        .estado(Estado.EN_PROGRESO)
                        .prioridad(Prioridad.MEDIA)
                        .creador(cliente)
                        .tecnico(tecnico)
                        .build();
                incidenciaRepository.save(incidencia2);

                System.out.println("✅ DATOS DE PRUEBA INYECTADOS CON ÉXITO");
            }
        };
    }
}