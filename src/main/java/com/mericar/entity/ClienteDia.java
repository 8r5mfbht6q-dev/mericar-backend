package com.mericar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "cliente_dias",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_cliente_dia",
                        columnNames = {
                                "id_cliente",
                                "id_detalle_parametro"
                        }
                )
        }
)
@Data
public class ClienteDia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente_dia")
    private Long idClienteDia;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "id_detalle_parametro", nullable = false)
    private Long idDetalleParametro;

    private Boolean estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}