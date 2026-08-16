package com.mericar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrega")
    private Long idEntrega;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nombre_cliente_ocasional")
    private String nombreClienteOcasional;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "abono", nullable = false)
    private BigDecimal abono;

    // PostgreSQL calcula automáticamente este campo
    @Column(
        name = "saldo_pendiente",
        insertable = false,
        updatable = false
    )
    private BigDecimal saldoPendiente;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "pago_efectivo", nullable = false)
    private BigDecimal pagoEfectivo;

    @Column(name = "pago_transferencia", nullable = false)
    private BigDecimal pagoTransferencia;

    @OneToMany(
        mappedBy = "entrega",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<DetalleEntrega> detalles = new ArrayList<>();
}