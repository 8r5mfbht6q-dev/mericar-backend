package com.mericar.repository;

import com.mericar.entity.EntregaPago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregaPagoRepository
        extends JpaRepository<EntregaPago, Long> {

    List<EntregaPago> findByIdEntregaAndActivoTrueOrderByFechaPagoAsc(
        Long idEntrega
    );
}