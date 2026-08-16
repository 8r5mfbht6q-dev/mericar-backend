package com.mericar.service;

import com.mericar.dto.LoginRequest;
import com.mericar.dto.LoginResponse;
import com.mericar.entity.Usuario;
import com.mericar.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    // ==========================================
    // LOGIN
    // ==========================================

    public LoginResponse login(
            LoginRequest request
    ) {

        // ==========================================
        // VALIDAR DATOS
        // ==========================================

        if (
                request.getUsuario() == null ||
                request.getUsuario().trim().isEmpty()
        ) {

            return respuestaError(
                    "Ingrese el usuario"
            );
        }


        if (
                request.getPassword() == null ||
                request.getPassword().isEmpty()
        ) {

            return respuestaError(
                    "Ingrese la contraseña"
            );
        }


        // ==========================================
        // BUSCAR USUARIO
        // ==========================================

        Usuario usuario =
                usuarioRepository
                        .findByUsuario(
                                request
                                        .getUsuario()
                                        .trim()
                        )
                        .orElse(null);


        // ==========================================
        // USUARIO NO EXISTE
        // ==========================================

        if (usuario == null) {

            return respuestaError(
                    "Usuario no existe"
            );
        }


        // ==========================================
        // USUARIO INACTIVO
        // ==========================================

        if (
                !Boolean.TRUE.equals(
                        usuario.getActivo()
                )
        ) {

            return respuestaError(
                    "Usuario inactivo"
            );
        }


        // ==========================================
        // CONTRASEÑA INCORRECTA
        // ==========================================

        if (
                usuario.getPasswordHash() == null ||
                !passwordEncoder.matches(
                        request.getPassword(),
                        usuario.getPasswordHash()
                )
        ) {

            return respuestaError(
                    "Contraseña incorrecta"
            );
        }


        // ==========================================
        // OBTENER ROL
        // ==========================================

        String rol =
                usuario.getRol() != null
                        ? usuario.getRol().toString()
                        : null;


        // ==========================================
        // LOGIN CORRECTO
        // ==========================================

        return new LoginResponse(
                true,
                "Login correcto",
                usuario.getIdUsuario(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getCorreo(),
                usuario.getUsuario(),
                rol
        );
    }


    // ==========================================
    // RESPUESTA DE ERROR
    // ==========================================

    private LoginResponse respuestaError(
            String mensaje
    ) {

        return new LoginResponse(
                false,
                mensaje,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}