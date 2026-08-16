package com.mericar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros_stock")
@Data
public class RegistroStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_stock")
    private Long idRegistroStock;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
}