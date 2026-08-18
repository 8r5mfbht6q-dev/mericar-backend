package com.mericar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_dispositivos")
@Data
public class UsuarioDispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_dispositivo")
    private Long idUsuarioDispositivo;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(
        name = "expo_push_token",
        nullable = false,
        unique = true
    )
    private String expoPushToken;

    @Column(name = "plataforma")
    private String plataforma;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}