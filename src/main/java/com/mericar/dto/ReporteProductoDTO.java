package com.mericar.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ReporteProductoDTO {

    private Long idProducto;

    private String producto;

    private Long cantidadVendida;

    private BigDecimal totalVendido;
}