package com.mericar.service;

import com.mericar.entity.UsuarioDispositivo;
import com.mericar.repository.UsuarioDispositivoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UsuarioDispositivoService {

    @Autowired
    private UsuarioDispositivoRepository repository;


    @Transactional
    public UsuarioDispositivo registrar(
            Long idUsuario,
            String expoPushToken,
            String plataforma
    ) {

        UsuarioDispositivo dispositivo =
                repository
                    .findByExpoPushToken(expoPushToken)
                    .orElse(new UsuarioDispositivo());

        dispositivo.setIdUsuario(idUsuario);
        dispositivo.setExpoPushToken(expoPushToken);
        dispositivo.setPlataforma(plataforma);
        dispositivo.setActivo(true);
        dispositivo.setFechaActualizacion(LocalDateTime.now());

        if (dispositivo.getIdUsuarioDispositivo() == null) {
            dispositivo.setFechaCreacion(LocalDateTime.now());
        }

        return repository.save(dispositivo);
    }
}