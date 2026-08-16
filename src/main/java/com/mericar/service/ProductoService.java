package com.mericar.service;

import com.mericar.entity.Producto;
import com.mericar.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado")
                );
    }

    public Producto guardar(Producto producto) {

        producto.setIdProducto(null);

        if (producto.getStockActual() == null) {
            producto.setStockActual(0);
        }

        producto.setActivo(true);
        producto.setFechaCreacion(LocalDateTime.now());
        producto.setFechaActualizacion(LocalDateTime.now());

        return repository.save(producto);
    }

    public Producto actualizarPrecio(Long id, BigDecimal precio) {

    Producto producto = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Producto no encontrado")
            );

    producto.setPrecio(precio);
    producto.setFechaActualizacion(LocalDateTime.now());

    return repository.save(producto);
}

public Producto cambiarEstado(Long id, Boolean activo) {

    Producto producto = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Producto no encontrado")
            );

    producto.setActivo(activo);
    producto.setFechaActualizacion(LocalDateTime.now());

    return repository.save(producto);
}
}