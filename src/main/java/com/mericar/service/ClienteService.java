package com.mericar.service;

import com.mericar.dto.ClienteRequest;
import com.mericar.dto.ClienteResponse;
import com.mericar.dto.DiaResponse;

import com.mericar.entity.Cliente;
import com.mericar.entity.ClienteDia;
import com.mericar.entity.DetalleParametro;

import com.mericar.repository.ClienteDiaRepository;
import com.mericar.repository.ClienteRepository;
import com.mericar.repository.DetalleParametroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteDiaRepository clienteDiaRepository;

    @Autowired
    private DetalleParametroRepository detalleParametroRepository;


    // ==========================================
    // LISTAR TODOS
    // ==========================================

    public List<Cliente> listar() {
        return repository.findAll();
    }


    public List<Cliente> listarPorDia(Long idDetalleParametro) {

    List<ClienteDia> relaciones =
            clienteDiaRepository
                    .findByIdDetalleParametroAndEstadoTrue(
                            idDetalleParametro
                    );

    List<Cliente> clientes = new ArrayList<>();

    for (ClienteDia relacion : relaciones) {

        repository
                .findById(relacion.getIdCliente())
                .filter(cliente ->
                        Boolean.TRUE.equals(cliente.getActivo())
                )
                .ifPresent(clientes::add);
    }

    return clientes;
}


    // ==========================================
    // GUARDAR
    // ==========================================

    @Transactional
    public Cliente guardar(ClienteRequest datos) {

        if (datos.getDias() == null || datos.getDias().isEmpty()) {
            throw new RuntimeException(
                    "Debe seleccionar al menos un día"
            );
        }

        Cliente cliente = new Cliente();

        cliente.setNombres(datos.getNombres());
        cliente.setApellidos(datos.getApellidos());
        cliente.setCedula(datos.getCedula());
        cliente.setTelefono(datos.getTelefono());
        cliente.setCorreo(datos.getCorreo());
        cliente.setDireccion(datos.getDireccion());
        cliente.setObservacion(datos.getObservacion());

        cliente.setActivo(true);

        // Temporal mientras terminamos la migración
        

        cliente.setFechaRegistro(LocalDate.now());
        cliente.setFechaActualizacion(LocalDateTime.now());

        Cliente nuevo = repository.save(cliente);

        for (Long idDetalleParametro : datos.getDias()) {

            ClienteDia clienteDia = new ClienteDia();

            clienteDia.setIdCliente(nuevo.getIdCliente());
            clienteDia.setIdDetalleParametro(idDetalleParametro);
            clienteDia.setEstado(true);
            clienteDia.setFechaCreacion(LocalDateTime.now());

            clienteDiaRepository.save(clienteDia);
        }

        return nuevo;
    }


    // ==========================================
    // OBTENER POR ID CON SUS DÍAS
    // ==========================================

    public ClienteResponse obtener(Long id) {

        Cliente cliente = repository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cliente no encontrado"
                        )
                );

        ClienteResponse response = new ClienteResponse();

        response.setIdCliente(cliente.getIdCliente());
        response.setNombres(cliente.getNombres());
        response.setApellidos(cliente.getApellidos());
        response.setCedula(cliente.getCedula());
        response.setTelefono(cliente.getTelefono());
        response.setCorreo(cliente.getCorreo());
        response.setDireccion(cliente.getDireccion());
        response.setObservacion(cliente.getObservacion());
        response.setActivo(cliente.getActivo());
        response.setFechaRegistro(cliente.getFechaRegistro());
        response.setFechaActualizacion(cliente.getFechaActualizacion());

        List<ClienteDia> clienteDias =
                clienteDiaRepository
                        .findByIdClienteAndEstadoTrue(id);

        List<DiaResponse> dias = new ArrayList<>();

        for (ClienteDia clienteDia : clienteDias) {

            DetalleParametro detalle =
                    detalleParametroRepository
                            .findByIdDetalleParametroAndEstadoTrue(
                                    clienteDia.getIdDetalleParametro()
                            )
                            .orElse(null);

            if (detalle != null) {

                DiaResponse dia = new DiaResponse();

                dia.setIdDetalleParametro(
                        detalle.getIdDetalleParametro()
                );

                dia.setNombre(
                        detalle.getNombre()
                );

                dia.setValor(
                        detalle.getValor()
                );

                dias.add(dia);
            }
        }

        response.setDias(dias);

        return response;
    }


    // ==========================================
    // ACTUALIZAR CLIENTE Y DÍAS
    // ==========================================

    @Transactional
    public Cliente actualizar(
            Long id,
            ClienteRequest datos
    ) {

        if (datos.getDias() == null || datos.getDias().isEmpty()) {
            throw new RuntimeException(
                    "Debe seleccionar al menos un día"
            );
        }

        Cliente cliente =
                repository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cliente no encontrado"
                                )
                        );

        // ======================================
        // ACTUALIZAR DATOS DEL CLIENTE
        // ======================================

        cliente.setNombres(datos.getNombres());
        cliente.setApellidos(datos.getApellidos());
        cliente.setCedula(datos.getCedula());
        cliente.setTelefono(datos.getTelefono());
        cliente.setCorreo(datos.getCorreo());
        cliente.setDireccion(datos.getDireccion());
        cliente.setObservacion(datos.getObservacion());

        cliente.setFechaActualizacion(
                LocalDateTime.now()
        );

        Cliente actualizado = repository.save(cliente);


        // ======================================
        // DESACTIVAR DÍAS NO SELECCIONADOS
        // ======================================

        List<ClienteDia> diasExistentes =
                clienteDiaRepository.findByIdCliente(id);

        for (ClienteDia clienteDia : diasExistentes) {

            boolean seleccionado =
                    datos.getDias().contains(
                            clienteDia.getIdDetalleParametro()
                    );

            if (!seleccionado) {

                clienteDia.setEstado(false);
                clienteDia.setFechaActualizacion(
                        LocalDateTime.now()
                );

                clienteDiaRepository.save(clienteDia);
            }
        }


        // ======================================
        // ACTIVAR O CREAR DÍAS SELECCIONADOS
        // ======================================

        for (Long idDetalleParametro : datos.getDias()) {

            ClienteDia clienteDia =
                    clienteDiaRepository
                            .findByIdClienteAndIdDetalleParametro(
                                    id,
                                    idDetalleParametro
                            )
                            .orElse(null);

            // Ya existía
            if (clienteDia != null) {

                clienteDia.setEstado(true);

                clienteDia.setFechaActualizacion(
                        LocalDateTime.now()
                );

                clienteDiaRepository.save(clienteDia);

            } else {

                // Nunca había sido asignado
                ClienteDia nuevoDia = new ClienteDia();

                nuevoDia.setIdCliente(id);
                nuevoDia.setIdDetalleParametro(
                        idDetalleParametro
                );
                nuevoDia.setEstado(true);
                nuevoDia.setFechaCreacion(
                        LocalDateTime.now()
                );

                clienteDiaRepository.save(nuevoDia);
            }
        }

        return actualizado;
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