package com.mericar.service;

import com.mericar.entity.Cliente;
import com.mericar.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;


    // ==========================================
    // LISTAR TODOS
    // ==========================================

    public List<Cliente> listar() {
        return repository.findAll();
    }


    // ==========================================
    // LISTAR CLIENTES ACTIVOS POR DÍA
    // ==========================================

    public List<Cliente> listarPorDia(Short idDia) {
    return repository.findByIdDiaAndActivoTrue(idDia);
}


    // ==========================================
    // GUARDAR
    // ==========================================

    public Cliente guardar(Cliente cliente) {

        try {

            cliente.setActivo(true);

            // Temporalmente lunes por defecto
            cliente.setIdDia((short) 1);

            cliente.setFechaRegistro(
                    java.time.LocalDate.now()
            );

            cliente.setFechaActualizacion(
                    LocalDateTime.now()
            );

            return repository.save(cliente);

        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }


    // ==========================================
    // OBTENER POR ID
    // ==========================================

    public Cliente obtener(Long id) {

        return repository
                .findById(id)
                .orElse(null);
    }


    // ==========================================
    // ACTUALIZAR
    // ==========================================

    public Cliente actualizar(
            Long id,
            Cliente datos
    ) {

        Cliente cliente =
                repository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cliente no encontrado"
                                )
                        );

        cliente.setNombres(
                datos.getNombres()
        );

        cliente.setApellidos(
                datos.getApellidos()
        );

        cliente.setCedula(
                datos.getCedula()
        );

        cliente.setTelefono(
                datos.getTelefono()
        );

        cliente.setCorreo(
                datos.getCorreo()
        );

        cliente.setDireccion(
                datos.getDireccion()
        );

        cliente.setObservacion(
                datos.getObservacion()
        );

        // IMPORTANTE:
        // permitimos actualizar el día
        if (datos.getIdDia() != null) {
            cliente.setIdDia(
                    datos.getIdDia()
            );
        }

        cliente.setFechaActualizacion(
                LocalDateTime.now()
        );

        return repository.save(cliente);
    }


    // ==========================================
    // CAMBIAR ESTADO
    // ==========================================

    public Cliente cambiarEstado(
            Long id,
            Boolean activo
    ) {

        Cliente cliente =
                repository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cliente no encontrado"
                                )
                        );

        cliente.setActivo(activo);

        cliente.setFechaActualizacion(
                LocalDateTime.now()
        );

        return repository.save(cliente);
    }
}