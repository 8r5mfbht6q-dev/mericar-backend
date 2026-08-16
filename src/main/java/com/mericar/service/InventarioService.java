package com.mericar.service;

import com.mericar.dto.MovimientoStockRequest;
import com.mericar.entity.DetalleRegistroStock;
import com.mericar.entity.Producto;
import com.mericar.entity.RegistroStock;
import com.mericar.repository.DetalleRegistroStockRepository;
import com.mericar.repository.ProductoRepository;
import com.mericar.repository.RegistroStockRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class InventarioService {

    private final ProductoRepository productoRepository;
    private final RegistroStockRepository registroStockRepository;
    private final DetalleRegistroStockRepository detalleRegistroStockRepository;

    public InventarioService(
            ProductoRepository productoRepository,
            RegistroStockRepository registroStockRepository,
            DetalleRegistroStockRepository detalleRegistroStockRepository
    ) {
        this.productoRepository = productoRepository;
        this.registroStockRepository = registroStockRepository;
        this.detalleRegistroStockRepository = detalleRegistroStockRepository;
    }

    // ==========================================
    // REGISTRAR MOVIMIENTO
    // ==========================================

    @Transactional
    public Producto registrarMovimiento(
            MovimientoStockRequest request
    ) {

        // ======================================
        // VALIDACIONES
        // ======================================

        if (request.getIdProducto() == null) {
            throw new RuntimeException(
                    "Debe seleccionar un producto"
            );
        }

        if (
                request.getCantidad() == null ||
                request.getCantidad() <= 0
        ) {
            throw new RuntimeException(
                    "La cantidad debe ser mayor a cero"
            );
        }

        if (
                request.getTipoMovimiento() == null ||
                request.getTipoMovimiento().isBlank()
        ) {
            throw new RuntimeException(
                    "Debe indicar el tipo de movimiento"
            );
        }

        if (request.getIdUsuario() == null) {
            throw new RuntimeException(
                    "Debe indicar el usuario"
            );
        }

        // ======================================
        // BUSCAR PRODUCTO
        // ======================================

        Producto producto = productoRepository
                .findById(request.getIdProducto())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Producto no encontrado"
                        )
                );

        if (!Boolean.TRUE.equals(producto.getActivo())) {
            throw new RuntimeException(
                    "No se pueden realizar movimientos sobre un producto inactivo"
            );
        }

        int stockAnterior =
                producto.getStockActual() != null
                        ? producto.getStockActual()
                        : 0;

        int stockNuevo;

        String tipo = request
                .getTipoMovimiento()
                .trim()
                .toUpperCase();

        // ======================================
        // CALCULAR STOCK
        // ======================================

        switch (tipo) {

            case "ENTRADA":

                stockNuevo =
                        stockAnterior +
                        request.getCantidad();

                break;

            case "SALIDA":

                if (request.getCantidad() > stockAnterior) {
                    throw new RuntimeException(
                            "Stock insuficiente. Disponible: "
                                    + stockAnterior
                    );
                }

                stockNuevo =
                        stockAnterior -
                        request.getCantidad();

                break;

            case "AJUSTE":

                // En AJUSTE, cantidad representa
                // el stock físico real contado.

                stockNuevo = request.getCantidad();

                break;

            default:

                throw new RuntimeException(
                        "Tipo de movimiento inválido"
                );
        }

        // ======================================
        // CREAR CABECERA
        // ======================================

        RegistroStock registro =
                new RegistroStock();

        registro.setFecha(LocalDate.now());

        registro.setIdUsuario(
                request.getIdUsuario()
        );

        registro.setObservacion(
                request.getObservacion()
        );

        registro.setFechaCreacion(
                LocalDateTime.now()
        );

        registro =
                registroStockRepository.save(registro);

        // ======================================
        // CREAR DETALLE
        // ======================================

        DetalleRegistroStock detalle =
                new DetalleRegistroStock();

        detalle.setIdRegistroStock(
                registro.getIdRegistroStock()
        );

        detalle.setIdProducto(
                producto.getIdProducto()
        );

        detalle.setCantidad(
                request.getCantidad()
        );

        detalle.setTipoMovimiento(tipo);

        detalle.setStockAnterior(
                stockAnterior
        );

        detalle.setStockNuevo(
                stockNuevo
        );

        detalleRegistroStockRepository.save(
                detalle
        );

        // ======================================
        // ACTUALIZAR PRODUCTO
        // ======================================

        producto.setStockActual(stockNuevo);

        producto.setFechaActualizacion(
                LocalDateTime.now()
        );

        return productoRepository.save(producto);
    }
}