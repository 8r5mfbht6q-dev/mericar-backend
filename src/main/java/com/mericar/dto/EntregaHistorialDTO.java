package com.mericar.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntregaHistorialDTO {

    private Long idEntrega;
    private LocalDate fecha;

    private BigDecimal total;
    private BigDecimal abono;
    private BigDecimal saldoPendiente;

    private String metodoPago;

    private BigDecimal pagoEfectivo;
    private BigDecimal pagoTransferencia;

    private String observacion;

    private List<DetalleEntregaHistorialDTO> productos =
        new ArrayList<>();


    public EntregaHistorialDTO() {
    }


    public Long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Long idEntrega) {
        this.idEntrega = idEntrega;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    public BigDecimal getAbono() {
        return abono;
    }

    public void setAbono(BigDecimal abono) {
        this.abono = abono;
    }


    public BigDecimal getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(BigDecimal saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }


    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }


    public BigDecimal getPagoEfectivo() {
        return pagoEfectivo;
    }

    public void setPagoEfectivo(BigDecimal pagoEfectivo) {
        this.pagoEfectivo = pagoEfectivo;
    }


    public BigDecimal getPagoTransferencia() {
        return pagoTransferencia;
    }

    public void setPagoTransferencia(
        BigDecimal pagoTransferencia
    ) {
        this.pagoTransferencia = pagoTransferencia;
    }


    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }


    public List<DetalleEntregaHistorialDTO> getProductos() {
        return productos;
    }

    public void setProductos(
        List<DetalleEntregaHistorialDTO> productos
    ) {
        this.productos = productos;
    }
}