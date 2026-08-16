package com.mericar.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ReporteInventarioDTO {

    private Long idProducto;

    private String producto;

    private BigDecimal precio;

    private Integer stockActual;

    private BigDecimal valorInventario;

    private Boolean activo;
}