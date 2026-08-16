package com.mericar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;

    private String nombres;

    private String apellidos;

    private String usuario;

    private String correo;
    @Column(name = "password_hash")
    private String passwordHash;

    private String rol;

    private Boolean activo;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

}