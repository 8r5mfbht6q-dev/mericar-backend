package com.mericar.controller;

import com.mericar.dto.ClienteRequest;
import com.mericar.entity.Cliente;
import com.mericar.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mericar.dto.ClienteResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteService service;


    // ==========================================
    // LISTAR TODOS
    // ==========================================

    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }


    // ==========================================
    // LISTAR CLIENTES POR DÍA
    // ==========================================

    @GetMapping("/dia/{idDetalleParametro}")
    public List<Cliente> listarPorDia(
            @PathVariable Long idDetalleParametro
    ) {
        return service.listarPorDia(idDetalleParametro);
    }


    // ==========================================
    // OBTENER CLIENTE
    // ==========================================

    @GetMapping("/{id}")
    public ClienteResponse obtener(
            @PathVariable Long id
    ) {
        return service.obtener(id);
    }


    // ==========================================
    // GUARDAR
    // ==========================================

    @PostMapping
    public ResponseEntity<?> guardar(
            @RequestBody ClienteRequest cliente
    ) {

        try {

            Cliente nuevo =
                    service.guardar(cliente);

            return ResponseEntity.ok(nuevo);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }


    // ==========================================
    // ACTUALIZAR
    // ==========================================

    @PutMapping("/{id}")
    public Cliente actualizar(
            @PathVariable Long id,
            @RequestBody ClienteRequest cliente
    ) {
        return service.actualizar(id, cliente);
    }


    // ==========================================
    // CAMBIAR ESTADO
    // ==========================================

    @PatchMapping("/{id}/estado")
    public Cliente cambiarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> body
    ) {

        return service.cambiarEstado(
                id,
                body.get("activo")
        );
    }
}