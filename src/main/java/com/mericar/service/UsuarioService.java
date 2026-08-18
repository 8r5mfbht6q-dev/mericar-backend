package com.mericar.service;

import com.mericar.dto.UsuarioRegistroRequest;
import com.mericar.entity.Usuario;
import com.mericar.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mericar.entity.RolUsuario;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    // ==========================================
    // LISTAR USUARIOS
    // ==========================================

    public List<Usuario> listar() {

        return usuarioRepository
                .findAllByOrderByNombresAsc();
    }


    // ==========================================
    // REGISTRAR USUARIO
    // ==========================================

    @Transactional
    public Usuario registrar(
            UsuarioRegistroRequest request
    ) {

        // ==========================================
        // VALIDACIONES
        // ==========================================

        if (
            request.getNombres() == null ||
            request.getNombres().trim().isEmpty()
        ) {
            throw new RuntimeException(
                    "Ingrese los nombres del usuario."
            );
        }


        if (
            request.getApellidos() == null ||
            request.getApellidos().trim().isEmpty()
        ) {
            throw new RuntimeException(
                    "Ingrese los apellidos del usuario."
            );
        }


        if (
            request.getCorreo() == null ||
            request.getCorreo().trim().isEmpty()
        ) {
            throw new RuntimeException(
                    "Ingrese el correo electrónico."
            );
        }


        if (
            request.getUsuario() == null ||
            request.getUsuario().trim().isEmpty()
        ) {
            throw new RuntimeException(
                    "Ingrese el nombre de usuario."
            );
        }


        if (
            request.getPassword() == null ||
            request.getPassword().trim().isEmpty()
        ) {
            throw new RuntimeException(
                    "Ingrese la contraseña."
            );
        }


        if (
            request.getRol() == null ||
            request.getRol().trim().isEmpty()
        ) {
            throw new RuntimeException(
                    "Seleccione el rol."
            );
        }


        // ==========================================
        // VALIDAR USUARIO DUPLICADO
        // ==========================================

        String nombreUsuario =
                request.getUsuario().trim();


        if (
            usuarioRepository.existsByUsuario(
                    nombreUsuario
            )
        ) {
            throw new RuntimeException(
                    "El nombre de usuario ya se encuentra registrado."
            );
        }


        // ==========================================
        // VALIDAR CORREO DUPLICADO
        // ==========================================

        String correo =
                request.getCorreo()
                        .trim()
                        .toLowerCase();


        if (
            usuarioRepository.existsByCorreo(
                    correo
            )
        ) {
            throw new RuntimeException(
                    "El correo electrónico ya se encuentra registrado."
            );
        }


        // ==========================================
        // CREAR USUARIO
        // ==========================================

        Usuario usuario =
                new Usuario();


        usuario.setNombres(
                request.getNombres().trim()
        );


        usuario.setApellidos(
                request.getApellidos().trim()
        );


        usuario.setCorreo(
                correo
        );


        usuario.setUsuario(
                nombreUsuario
        );


        usuario.setPasswordHash(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );


        try {

        usuario.setRol(
                RolUsuario.valueOf(
                request.getRol()
                        .trim()
                        .toUpperCase()
                )
        );

        } catch (IllegalArgumentException e) {

        throw new RuntimeException(
                "El rol seleccionado no es válido."
        );
        }


        usuario.setActivo(
                true
        );


        usuario.setFechaCreacion(
                LocalDateTime.now()
        );


        usuario.setFechaActualizacion(
                LocalDateTime.now()
        );


        return usuarioRepository.save(
                usuario
        );
    }


    // ==========================================
    // ACTIVAR / DESACTIVAR USUARIO
    // ==========================================

@Transactional
public boolean cambiarEstado(
        Long idUsuario
) {

    Usuario usuario =
            usuarioRepository
                    .findById(idUsuario)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Usuario no encontrado."
                            )
                    );


    boolean nuevoEstado =
            !Boolean.TRUE.equals(
                    usuario.getActivo()
            );


    int actualizados =
            usuarioRepository.actualizarEstado(
                    idUsuario,
                    nuevoEstado
            );


    if (actualizados == 0) {

        throw new RuntimeException(
                "No se pudo actualizar el estado del usuario."
        );
    }


    return nuevoEstado;
}
}