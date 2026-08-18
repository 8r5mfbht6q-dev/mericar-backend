package com.mericar.repository;

import com.mericar.entity.UsuarioDispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDispositivoRepository
        extends JpaRepository<UsuarioDispositivo, Long> {

    Optional<UsuarioDispositivo> findByExpoPushToken(
            String expoPushToken
    );
}