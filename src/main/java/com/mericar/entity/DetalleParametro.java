package com.mericar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "detalle_parametros")
@Data
public class DetalleParametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_parametro")
    private Long idDetalleParametro;

    @Column(name = "id_parametro", nullable = false)
    private Long idParametro;

    private String nombre;

    private String descripcion;

    @Column(name = "rango_inicial")
    private String rangoInicial;

    @Column(name = "rango_final")
    private String rangoFinal;

    private String valor;

    private Boolean estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}