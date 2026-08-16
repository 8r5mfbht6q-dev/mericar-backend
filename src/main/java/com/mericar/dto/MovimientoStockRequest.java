package com.mericar.dto;

import lombok.Data;

@Data
public class MovimientoStockRequest {

    private Long idProducto;

    private Integer cantidad;

    private String tipoMovimiento;

    private String observacion;

    private Long idUsuario;
}