package com.mericar.controller;

import com.mericar.service.ReporteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin("*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;


    // ==========================================
    // RESUMEN GENERAL
    // ==========================================

    @GetMapping("/resumen")
    public ResponseEntity<?> obtenerResumen() {

        try {

            Map<String, Object> resumen =
                reporteService.obtenerResumen();


            Map<String, Object> respuesta =
                new HashMap<>();

            respuesta.put(
                "success",
                true
            );

            respuesta.putAll(
                resumen
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
                "No se pudo obtener el resumen de reportes"
            );


            return ResponseEntity
                .badRequest()
                .body(respuesta);
        }
    }
}