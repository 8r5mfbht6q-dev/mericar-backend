package com.mericar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private boolean success;
    private String mensaje;

    private Long idUsuario;
    private String nombres;
    private String apellidos;
    private String correo;
    private String usuario;
    private String rol;
}