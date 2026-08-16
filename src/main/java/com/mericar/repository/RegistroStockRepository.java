package com.mericar.repository;

import com.mericar.entity.RegistroStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroStockRepository
        extends JpaRepository<RegistroStock, Long> {
}