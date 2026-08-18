package com.mericar.controller;

import com.mericar.dto.DispositivoRequest;
import com.mericar.entity.UsuarioDispositivo;
import com.mericar.service.UsuarioDispositivoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dispositivos")
@CrossOrigin(origins = "*")
public class UsuarioDispositivoController {

    @Autowired
    private UsuarioDispositivoService service;


    // ==========================================
    // REGISTRAR / ACTUALIZAR DISPOSITIVO
    // ==========================================

    @PostMapping
    public ResponseEntity<UsuarioDispositivo> registrar(
            @RequestBody DispositivoRequest request
    ) {

        UsuarioDispositivo dispositivo =
                service.registrar(
                        request.getIdUsuario(),
                        request.getExpoPushToken(),
                        request.getPlataforma()
                );

        return ResponseEntity.ok(dispositivo);
    }
}