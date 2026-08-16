package com.mericar.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class EntregaRequest {

    private Long idCliente;

    private String nombreClienteOcasional;

    private BigDecimal total;

    private BigDecimal abono;

    private String metodoPago;

    private BigDecimal pagoEfectivo;

    private BigDecimal pagoTransferencia;

    private String observacion;

    private Long idUsuario;

    private String tipo;

    private List<DetalleEntregaRequest> productos;
}