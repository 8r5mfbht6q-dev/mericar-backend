package com.mericar.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleEntregaRequest {

    private Long idProducto;

    private Integer cantidad;

    private BigDecimal precio;
}