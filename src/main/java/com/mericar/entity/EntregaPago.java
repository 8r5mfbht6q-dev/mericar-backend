package com.mericar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "entrega_pagos")
public class EntregaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @Column(name = "id_entrega", nullable = false)
    private Long idEntrega;

    @Column(name = "monto", nullable = false)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @Column(name = "pago_efectivo", nullable = false)
    private BigDecimal pagoEfectivo;

    @Column(name = "pago_transferencia", nullable = false)
    private BigDecimal pagoTransferencia;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
}