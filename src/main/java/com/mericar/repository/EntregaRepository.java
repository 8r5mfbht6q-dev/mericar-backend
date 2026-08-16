package com.mericar.repository;

import com.mericar.entity.Entrega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EntregaRepository
        extends JpaRepository<Entrega, Long> {

    // ==========================================
    // HISTORIAL POR CLIENTE
    // ==========================================

    List<Entrega> findByIdClienteAndActivoTrueOrderByFechaDescIdEntregaDesc(
        Long idCliente
    );


    // ==========================================
    // TOTAL ENTREGADO
    // ==========================================

    @Query("""
        SELECT COALESCE(SUM(e.total), 0)
        FROM Entrega e
        WHERE e.activo = true
    """)
    BigDecimal obtenerTotalEntregado();


    // ==========================================
    // TOTAL COBRADO
    // ==========================================

    @Query("""
        SELECT COALESCE(SUM(e.abono), 0)
        FROM Entrega e
        WHERE e.activo = true
    """)
    BigDecimal obtenerTotalCobrado();


    // ==========================================
    // SALDO PENDIENTE
    // ==========================================

    @Query("""
        SELECT COALESCE(SUM(e.saldoPendiente), 0)
        FROM Entrega e
        WHERE e.activo = true
    """)
    BigDecimal obtenerSaldoPendiente();


    // ==========================================
    // CANTIDAD DE ENTREGAS
    // ==========================================

    long countByActivoTrue();
    // ==========================================
// CUENTAS POR COBRAR
// ==========================================

List<Entrega> findByActivoTrueAndSaldoPendienteGreaterThanOrderByFechaAsc(
    BigDecimal saldoPendiente
);

// ==========================================
// REPORTE ENTREGAS POR CLIENTE
// ==========================================

@Query("""
    SELECT
        e.idCliente,
        COUNT(e),
        COALESCE(SUM(e.total), 0),
        COALESCE(SUM(e.abono), 0),
        COALESCE(SUM(e.saldoPendiente), 0)
    FROM Entrega e
    WHERE e.activo = true
      AND e.idCliente IS NOT NULL
    GROUP BY e.idCliente
    ORDER BY SUM(e.total) DESC
""")
List<Object[]> obtenerReportePorCliente();

// ==========================================
// REPORTE PRODUCTOS VENDIDOS
// ==========================================

@Query("""
    SELECT
        d.idProducto,
        SUM(d.cantidad),
        COALESCE(SUM(d.precioUnitario * d.cantidad), 0)
    FROM DetalleEntrega d
    JOIN d.entrega e
    WHERE e.activo = true
    GROUP BY d.idProducto
    ORDER BY SUM(d.cantidad) DESC
""")
List<Object[]> obtenerReporteProductos();




}