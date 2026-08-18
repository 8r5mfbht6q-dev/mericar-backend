package com.mericar.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClienteRequest {

    private String nombres;

    private String apellidos;

    private String cedula;

    private String telefono;

    private String correo;

    private String direccion;

    private String observacion;

    private List<Long> dias;
}