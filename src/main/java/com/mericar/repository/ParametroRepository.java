package com.mericar.repository;

import com.mericar.entity.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametroRepository
        extends JpaRepository<Parametro, Long> {

    Optional<Parametro> findByCodigoAndEstadoTrue(String codigo);

}