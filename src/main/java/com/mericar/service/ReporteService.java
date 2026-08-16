package com.mericar.service;

import com.mericar.repository.EntregaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReporteService {

    @Autowired
    private EntregaRepository entregaRepository;


    // ==========================================
    // RESUMEN GENERAL
    // ==========================================

    public Map<String, Object> obtenerResumen() {

        BigDecimal totalEntregado =
            entregaRepository
                .obtenerTotalEntregado();

        BigDecimal totalCobrado =
            entregaRepository
                .obtenerTotalCobrado();

        BigDecimal saldoPendiente =
            entregaRepository
                .obtenerSaldoPendiente();

        long cantidadEntregas =
            entregaRepository
                .countByActivoTrue();


        Map<String, Object> resumen =
            new HashMap<>();

        resumen.put(
            "totalEntregado",
            totalEntregado
        );

        resumen.put(
            "totalCobrado",
            totalCobrado
        );

        resumen.put(
            "saldoPendiente",
            saldoPendiente
        );

        resumen.put(
            "cantidadEntregas",
            cantidadEntregas
        );


        return resumen;
    }
}