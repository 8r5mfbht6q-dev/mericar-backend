package com.mericar.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CuentaCobrarDTO {

    private Long idEntrega;

    private Long idCliente;

    private String cliente;

    private String telefono;

    private LocalDate fecha;

    private BigDecimal total;

    private BigDecimal abono;

    private BigDecimal saldoPendiente;
}