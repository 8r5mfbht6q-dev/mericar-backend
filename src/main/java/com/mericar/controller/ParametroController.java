package com.mericar.controller;

import com.mericar.entity.DetalleParametro;
import com.mericar.service.ParametroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parametros")
@CrossOrigin(origins = "*")
public class ParametroController {

    @Autowired
    private ParametroService parametroService;


    // ==========================================
    // OBTENER DETALLES POR CÓDIGO
    // ==========================================

    @GetMapping("/{codigo}")
    public ResponseEntity<List<DetalleParametro>> obtenerPorCodigo(
            @PathVariable String codigo
    ) {

        List<DetalleParametro> detalles =
                parametroService.obtenerDetallesPorCodigo(codigo);

        return ResponseEntity.ok(detalles);
    }
}