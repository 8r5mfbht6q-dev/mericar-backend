package com.mericar.controller;

import com.mericar.dto.UsuarioRegistroRequest;
import com.mericar.entity.Usuario;
import com.mericar.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<?> registrar(
            @RequestBody UsuarioRegistroRequest request
    ) {

        try {

            Usuario usuario =
                    usuarioService.registrar(request);


            Map<String, Object> respuesta =
                    new HashMap<>();

            respuesta.put(
                    "mensaje",
                    "Usuario registrado correctamente."
            );

            respuesta.put(
                    "idUsuario",
                    usuario.getIdUsuario()
            );


            return ResponseEntity.ok(
                    respuesta
            );

        } catch (RuntimeException e) {

            Map<String, String> respuesta =
                    new HashMap<>();

            respuesta.put(
                    "mensaje",
                    e.getMessage()
            );


            return ResponseEntity
                    .badRequest()
                    .body(respuesta);

        }

    }
    // ==========================================
// LISTAR USUARIOS
// ==========================================

@GetMapping
public ResponseEntity<?> listar() {

    try {

        List<Usuario> usuarios =
            usuarioService.listar();

        return ResponseEntity.ok(
            usuarios
        );

    } catch (Exception e) {

        e.printStackTrace();

        return ResponseEntity
            .badRequest()
            .body(
                Map.of(
                    "success",
                    false,
                    "mensaje",
                    "No se pudieron obtener los usuarios"
                )
            );
    }
}


// ==========================================
// ACTIVAR / DESACTIVAR USUARIO
// ==========================================

@PatchMapping("/{idUsuario}/estado")
public ResponseEntity<?> cambiarEstado(
        @PathVariable Long idUsuario
) {

    try {

        boolean activo =
                usuarioService.cambiarEstado(
                        idUsuario
                );


        Map<String, Object> respuesta =
                new HashMap<>();


        respuesta.put(
                "success",
                true
        );

        respuesta.put(
                "activo",
                activo
        );


        respuesta.put(
                "mensaje",
                activo
                        ? "Usuario activado correctamente."
                        : "Usuario desactivado correctamente."
        );


        return ResponseEntity.ok(
                respuesta
        );

    } catch (Exception e) {

        e.printStackTrace();


        Map<String, Object> respuesta =
                new HashMap<>();


        respuesta.put(
                "success",
                false
        );

        respuesta.put(
                "mensaje",
                e.getMessage()
        );


        return ResponseEntity
                .badRequest()
                .body(respuesta);
    }
}
}