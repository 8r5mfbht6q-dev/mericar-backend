package com.mericar.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ClienteResponse {

    private Long idCliente;

    private String nombres;

    private String apellidos;

    private String cedula;

    private String telefono;

    private String correo;

    private String direccion;

    private String observacion;

    private Boolean activo;

    private LocalDate fechaRegistro;

    private LocalDateTime fechaActualizacion;

    private List<DiaResponse> dias;
}