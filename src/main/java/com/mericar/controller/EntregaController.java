package com.mericar.controller;

import com.mericar.dto.EntregaRequest;
import com.mericar.dto.ReporteClienteDTO;
import com.mericar.entity.Entrega;
import com.mericar.service.EntregaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import com.mericar.dto.CuentaCobrarDTO;
import com.mericar.dto.EntregaHistorialDTO;
import java.util.List;
import com.mericar.dto.CuentaCobrarDTO;
import com.mericar.dto.ReporteProductoDTO;
import com.mericar.dto.ReporteInventarioDTO;
@RestController
@RequestMapping("/api/entregas")
@CrossOrigin("*")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;


    @PostMapping
    public ResponseEntity<?> guardar(
        @RequestBody EntregaRequest request
    ) {

        try {

            Entrega entrega =
                entregaService.guardar(request);

            Map<String, Object> respuesta =
                new HashMap<>();

            respuesta.put(
                "success",
                true
            );

            respuesta.put(
                "mensaje",
                "Entrega registrada correctamente"
            );

            respuesta.put(
                "idEntrega",
                entrega.getIdEntrega()
            );

            respuesta.put(
                "total",
                entrega.getTotal()
            );

            respuesta.put(
                "abono",
                entrega.getAbono()
            );

            respuesta.put(
                "saldoPendiente",
                entrega.getSaldoPendiente()
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


    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<?> obtenerHistorialCliente(
        @PathVariable Long idCliente
    ) {

        try {

            List<EntregaHistorialDTO> entregas =
                entregaService
                    .obtenerHistorialCliente(
                        idCliente
                    );


            Map<String, Object> respuesta =
                new HashMap<>();


            respuesta.put(
                "success",
                true
            );

            respuesta.put(
                "entregas",
                entregas
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
                "No se pudo obtener el historial de entregas"
            );


            return ResponseEntity
                .badRequest()
                .body(respuesta);
        }
    }
    // ==========================================
    // REPORTE CUENTAS POR COBRAR
    // ==========================================

    @GetMapping("/cuentas-por-cobrar")
    public ResponseEntity<?> obtenerCuentasPorCobrar() {

        try {

            List<CuentaCobrarDTO> cuentas =
                entregaService.obtenerCuentasPorCobrar();


            Map<String, Object> respuesta =
                new HashMap<>();

            respuesta.put(
                "success",
                true
            );

            respuesta.put(
                "cuentas",
                cuentas
            );

            respuesta.put(
                "cantidad",
                cuentas.size()
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
                "No se pudieron obtener las cuentas por cobrar"
            );


            return ResponseEntity
                .badRequest()
                .body(respuesta);
        }
    }
    // ==========================================
    // REPORTE ENTREGAS POR CLIENTE
    // ==========================================

    @GetMapping("/reporte-clientes")
    public ResponseEntity<?> obtenerReporteClientes() {

        try {

            List<ReporteClienteDTO> clientes =
                entregaService.obtenerReportePorCliente();


            Map<String, Object> respuesta =
                new HashMap<>();


            respuesta.put(
                "success",
                true
            );

            respuesta.put(
                "clientes",
                clientes
            );

            respuesta.put(
                "cantidad",
                clientes.size()
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
                "No se pudo obtener el reporte por cliente"
            );


            return ResponseEntity
                .badRequest()
                .body(respuesta);
        }
    }
    // ==========================================
    // REPORTE PRODUCTOS VENDIDOS
    // ==========================================

    @GetMapping("/reporte-productos")
    public ResponseEntity<?> obtenerReporteProductos() {

        try {

            List<ReporteProductoDTO> productos =
                entregaService.obtenerReporteProductos();

            Map<String, Object> respuesta =
                new HashMap<>();

            respuesta.put("success", true);
            respuesta.put("productos", productos);
            respuesta.put("cantidad", productos.size());

            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {

            e.printStackTrace();

            Map<String, Object> respuesta =
                new HashMap<>();

            respuesta.put("success", false);
            respuesta.put(
                "mensaje",
                "No se pudo obtener el reporte de productos vendidos"
            );

            return ResponseEntity
                .badRequest()
                .body(respuesta);
        }
    }
// ==========================================
// REPORTE INVENTARIO
// ==========================================

@GetMapping("/reporte-inventario")
public ResponseEntity<?> obtenerReporteInventario() {

    try {

        List<ReporteInventarioDTO> productos =
            entregaService.obtenerReporteInventario();


        Map<String, Object> respuesta =
            new HashMap<>();


        respuesta.put(
            "success",
            true
        );

        respuesta.put(
            "productos",
            productos
        );

        respuesta.put(
            "cantidad",
            productos.size()
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
            "No se pudo obtener el reporte de inventario"
        );


        return ResponseEntity
            .badRequest()
            .body(respuesta);
    }
}
}