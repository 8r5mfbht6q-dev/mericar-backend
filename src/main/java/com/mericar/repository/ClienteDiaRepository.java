package com.mericar.repository;

import com.mericar.entity.ClienteDia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ClienteDiaRepository
        extends JpaRepository<ClienteDia, Long> {

    // Días activos asignados a un cliente
    List<ClienteDia> findByIdClienteAndEstadoTrue(Long idCliente);

    // Todos los días asignados a un cliente
    List<ClienteDia> findByIdCliente(Long idCliente);

    // Verificar si ya existe una relación cliente - día
    boolean existsByIdClienteAndIdDetalleParametro(
            Long idCliente,
            Long idDetalleParametro
    );

    Optional<ClienteDia> findByIdClienteAndIdDetalleParametro(
        Long idCliente,
        Long idDetalleParametro
        );
        List<ClienteDia> findByIdDetalleParametroAndEstadoTrue(
                Long idDetalleParametro
        );
}