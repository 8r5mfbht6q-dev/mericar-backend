package com.mericar.controller;

import com.mericar.entity.Producto;
import com.mericar.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("*")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Producto obtener(
            @PathVariable Long id
    ) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<?> guardar(
            @RequestBody Producto producto
    ) {

        try {

            Producto nuevo =
                    service.guardar(producto);

            return ResponseEntity.ok(nuevo);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .internalServerError()
                    .body(
                        Map.of(
                            "message",
                            e.getMessage() != null
                                ? e.getMessage()
                                : "No se pudo registrar el producto"
                        )
                    );
        }
    }

    @PatchMapping("/{id}/precio")
public Producto actualizarPrecio(
        @PathVariable Long id,
        @RequestBody Map<String, BigDecimal> body
) {

    return service.actualizarPrecio(
            id,
            body.get("precio")
    );
}

    @PatchMapping("/{id}/estado")
public Producto cambiarEstado(
        @PathVariable Long id,
        @RequestBody Map<String, Boolean> body
) {

    return service.cambiarEstado(
            id,
            body.get("activo")
    );
}
}