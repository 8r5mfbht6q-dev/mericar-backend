package com.mericar.controller;

import com.mericar.dto.EntregaPagoRequest;
import com.mericar.entity.EntregaPago;
import com.mericar.service.EntregaPagoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/entregas")
@CrossOrigin("*")
public class EntregaPagoController {

    @Autowired
    private EntregaPagoService entregaPagoService;


    // ==========================================
    // REGISTRAR ABONO
    // ==========================================

    @PostMapping("/{idEntrega}/abonos")
    public ResponseEntity<?> registrarAbono(
        @PathVariable Long idEntrega,
        @RequestBody EntregaPagoRequest request
    ) {

        try {

            EntregaPago pago =
                entregaPagoService.registrarAbono(
                    idEntrega,
                    request
                );


            Map<String, Object> respuesta =
                new HashMap<>();


            respuesta.put(
                "success",
                true
            );

            respuesta.put(
                "mensaje",
                "Abono registrado correctamente"
            );

            respuesta.put(
                "idPago",
                pago.getIdPago()
            );

            respuesta.put(
                "monto",
                pago.getMonto()
            );

            respuesta.put(
                "metodoPago",
                pago.getMetodoPago()
            );

            respuesta.put(
                "pagoEfectivo",
                pago.getPagoEfectivo()
            );

            respuesta.put(
                "pagoTransferencia",
                pago.getPagoTransferencia()
            );

            respuesta.put(
                "fechaPago",
                pago.getFechaPago()
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
    // ==========================================
// LISTAR ABONOS
// ==========================================

@GetMapping("/{idEntrega}/abonos")
public ResponseEntity<?> obtenerAbonos(
    @PathVariable Long idEntrega
) {

    try {

        var pagos =
            entregaPagoService.obtenerAbonos(
                idEntrega
            );

        Map<String, Object> respuesta =
            new HashMap<>();

        respuesta.put(
            "success",
            true
        );

        respuesta.put(
            "abonos",
            pagos
        );

        return ResponseEntity.ok(
            respuesta
        );

    } catch (Exception e) {

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