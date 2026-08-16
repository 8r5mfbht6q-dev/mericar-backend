package com.mericar.service;

import com.mericar.dto.EntregaPagoRequest;
import com.mericar.entity.Entrega;
import com.mericar.entity.EntregaPago;
import com.mericar.entity.MetodoPago;
import com.mericar.repository.EntregaPagoRepository;
import com.mericar.repository.EntregaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class EntregaPagoService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaPagoRepository entregaPagoRepository;


    // ==========================================
    // REGISTRAR ABONO
    // ==========================================

    @Transactional
    public EntregaPago registrarAbono(
        Long idEntrega,
        EntregaPagoRequest request
    ) {

        // ==========================================
        // BUSCAR ENTREGA
        // ==========================================

        Entrega entrega =
            entregaRepository
                .findById(idEntrega)
                .orElseThrow(
                    () -> new RuntimeException(
                        "La entrega no existe"
                    )
                );


        // ==========================================
        // VALIDAR ENTREGA
        // ==========================================

        if (
            entrega.getActivo() != null &&
            !entrega.getActivo()
        ) {
            throw new RuntimeException(
                "La entrega se encuentra inactiva"
            );
        }


        BigDecimal saldoActual =
            entrega.getSaldoPendiente() != null
                ? entrega.getSaldoPendiente()
                : BigDecimal.ZERO;


        if (
            saldoActual.compareTo(
                BigDecimal.ZERO
            ) <= 0
        ) {
            throw new RuntimeException(
                "La entrega ya se encuentra pagada"
            );
        }


        // ==========================================
        // OBTENER PAGOS
        // ==========================================

        BigDecimal efectivo =
            request.getPagoEfectivo() != null
                ? request.getPagoEfectivo()
                : BigDecimal.ZERO;


        BigDecimal transferencia =
            request.getPagoTransferencia() != null
                ? request.getPagoTransferencia()
                : BigDecimal.ZERO;


        // ==========================================
        // VALIDAR VALORES
        // ==========================================

        if (
            efectivo.compareTo(
                BigDecimal.ZERO
            ) < 0 ||
            transferencia.compareTo(
                BigDecimal.ZERO
            ) < 0
        ) {
            throw new RuntimeException(
                "Los valores del pago no pueden ser negativos"
            );
        }


        BigDecimal monto =
            efectivo.add(
                transferencia
            );


        if (
            monto.compareTo(
                BigDecimal.ZERO
            ) <= 0
        ) {
            throw new RuntimeException(
                "El abono debe ser mayor a cero"
            );
        }


        if (
            monto.compareTo(
                saldoActual
            ) > 0
        ) {
            throw new RuntimeException(
                "El abono no puede ser mayor al saldo pendiente"
            );
        }


        // ==========================================
        // DETERMINAR MÉTODO
        // ==========================================

        MetodoPago metodoPago;


        if (
            efectivo.compareTo(
                BigDecimal.ZERO
            ) > 0 &&
            transferencia.compareTo(
                BigDecimal.ZERO
            ) > 0
        ) {

            metodoPago =
                MetodoPago.MIXTO;

        } else if (
            transferencia.compareTo(
                BigDecimal.ZERO
            ) > 0
        ) {

            metodoPago =
                MetodoPago.TRANSFERENCIA;

        } else {

            metodoPago =
                MetodoPago.EFECTIVO;
        }


        // ==========================================
        // CREAR PAGO
        // ==========================================

        EntregaPago pago =
            new EntregaPago();


        pago.setIdEntrega(
            idEntrega
        );

        pago.setMonto(
            monto
        );

        pago.setMetodoPago(
            metodoPago
        );

        pago.setPagoEfectivo(
            efectivo
        );

        pago.setPagoTransferencia(
            transferencia
        );

        pago.setFechaPago(
            LocalDateTime.now()
        );

        pago.setIdUsuario(
            request.getIdUsuario()
        );

        pago.setObservacion(
            request.getObservacion()
        );

        pago.setActivo(true);

        pago.setFechaCreacion(
            LocalDateTime.now()
        );


        // ==========================================
        // ACTUALIZAR ENTREGA
        // ==========================================

        BigDecimal abonoActual =
            entrega.getAbono() != null
                ? entrega.getAbono()
                : BigDecimal.ZERO;


        BigDecimal nuevoAbono =
            abonoActual.add(
                monto
            );


        BigDecimal nuevoSaldo =
            saldoActual.subtract(
                monto
            );


        entrega.setAbono(
            nuevoAbono
        );

        entrega.setSaldoPendiente(
            nuevoSaldo
        );

        entrega.setFechaActualizacion(
            LocalDateTime.now()
        );


        // ==========================================
        // GUARDAR
        // ==========================================

        entregaPagoRepository.save(
            pago
        );

        entregaRepository.save(
            entrega
        );


        return pago;
    }
    // ==========================================
// LISTAR ABONOS DE UNA ENTREGA
// ==========================================

public List<EntregaPago> obtenerAbonos(
    Long idEntrega
) {

    if (
        !entregaRepository.existsById(idEntrega)
    ) {
        throw new RuntimeException(
            "La entrega no existe"
        );
    }

    return entregaPagoRepository
        .findByIdEntregaAndActivoTrueOrderByFechaPagoAsc(
            idEntrega
        );
}
}