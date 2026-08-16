package com.mericar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detalle_registro_stock")
@Data
public class DetalleRegistroStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_stock")
    private Long idDetalleStock;

    @Column(name = "id_registro_stock", nullable = false)
    private Long idRegistroStock;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "tipo_movimiento", nullable = false)
    private String tipoMovimiento;

    @Column(name = "stock_anterior", nullable = false)
    private Integer stockAnterior;

    @Column(name = "stock_nuevo", nullable = false)
    private Integer stockNuevo;
}