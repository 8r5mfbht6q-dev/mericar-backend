package com.mericar.repository;

import com.mericar.entity.DetalleParametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleParametroRepository
        extends JpaRepository<DetalleParametro, Long> {

    List<DetalleParametro> findByIdParametroAndEstadoTrueOrderByIdDetalleParametroAsc(
            Long idParametro
    );

    Optional<DetalleParametro> findByIdDetalleParametroAndEstadoTrue(
            Long idDetalleParametro
    );
}