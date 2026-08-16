package com.mericar.dto;

import lombok.Data;

@Data
public class UsuarioRegistroRequest {

    private String nombres;

    private String apellidos;

    private String correo;

    private String usuario;

    private String password;

    private String rol;

}