package com.mericar.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EntregaPagoRequest {

    private BigDecimal pagoEfectivo;

    private BigDecimal pagoTransferencia;

    private Long idUsuario;

    private String observacion;
}