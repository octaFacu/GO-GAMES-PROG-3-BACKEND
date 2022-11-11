package com.example.grupo.belmg.paginavideojuegos.Repositorios;


import com.example.grupo.belmg.paginavideojuegos.Entidades.Compra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioCompra extends RepositorioBase<Compra, Long>{

    @Query("SELECT c FROM Compra c WHERE c.usuario.id = :idUsuario")
    List<Compra> compras(@Param("idUsuario") Long id);

    @Query(value = "SELECT * FROM compras WHERE fk_usuario = ?1", nativeQuery = true)
    List<Compra> comprasUsuario(long idUsuario);
}
