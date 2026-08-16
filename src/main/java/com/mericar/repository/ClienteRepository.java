package com.mericar.repository;

import com.mericar.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository
        extends JpaRepository<Cliente, Long> {

    List<Cliente> findByIdDiaAndActivoTrue(Short idDia);
}