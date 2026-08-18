package com.mericar.dto;

import lombok.Data;

@Data
public class DispositivoRequest {

    private Long idUsuario;

    private String expoPushToken;

    private String plataforma;
}