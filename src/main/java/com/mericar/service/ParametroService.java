package com.mericar.service;

import com.mericar.entity.DetalleParametro;
import com.mericar.entity.Parametro;
import com.mericar.repository.DetalleParametroRepository;
import com.mericar.repository.ParametroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametroService {

    @Autowired
    private ParametroRepository parametroRepository;

    @Autowired
    private DetalleParametroRepository detalleParametroRepository;


    // ==========================================
    // OBTENER DETALLES POR CÓDIGO
    // ==========================================

    public List<DetalleParametro> obtenerDetallesPorCodigo(
            String codigo
    ) {

        Parametro parametro = parametroRepository
                .findByCodigoAndEstadoTrue(codigo)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Parámetro no encontrado: " + codigo
                        )
                );

        return detalleParametroRepository
                .findByIdParametroAndEstadoTrueOrderByIdDetalleParametroAsc(
                        parametro.getIdParametro()
                );
    }
}