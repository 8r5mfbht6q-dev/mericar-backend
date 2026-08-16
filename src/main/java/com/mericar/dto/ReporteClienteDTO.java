package com.mericar.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ReporteClienteDTO {

    private Long idCliente;

    private String cliente;

    private String telefono;

    private Long cantidadEntregas;

    private BigDecimal totalEntregado;

    private BigDecimal totalAbonado;

    private BigDecimal saldoPendiente;
}