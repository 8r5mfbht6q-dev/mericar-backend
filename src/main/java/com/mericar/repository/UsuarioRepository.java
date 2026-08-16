package com.mericar.repository;

import com.mericar.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {


    // ==========================================
    // LOGIN
    // ==========================================

    Optional<Usuario> findByUsuario(String usuario);


    // ==========================================
    // VALIDACIONES
    // ==========================================

    boolean existsByUsuario(String usuario);

    boolean existsByCorreo(String correo);


    // ==========================================
    // LISTAR USUARIOS
    // ==========================================

    List<Usuario> findAllByOrderByNombresAsc();


    // ==========================================
    // CAMBIAR ESTADO
    // ==========================================

    @Modifying
    @Query(
        value = """
            UPDATE usuarios
            SET activo = :activo,
                fecha_actualizacion = CURRENT_TIMESTAMP
            WHERE id_usuario = :idUsuario
        """,
        nativeQuery = true
    )
    int actualizarEstado(
        @Param("idUsuario") Long idUsuario,
        @Param("activo") Boolean activo
    );

}