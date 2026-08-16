package com.mericar.repository;

import com.mericar.entity.DetalleRegistroStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRegistroStockRepository
        extends JpaRepository<DetalleRegistroStock, Long> {
}