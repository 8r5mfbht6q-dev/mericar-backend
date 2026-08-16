package com.mericar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "detalle_entregas")
public class DetalleEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_entrega")
    private Long idDetalleEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "id_entrega",
        nullable = false
    )
    private Entrega entrega;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @Column(
        name = "subtotal",
        insertable = false,
        updatable = false
    )
    private BigDecimal subtotal;
}