package com.example.grupo.belmg.paginavideojuegos.Repositorios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Imagen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioImagen extends RepositorioBase<Imagen, Long>{

    @Query("SELECT c FROM Imagen c WHERE c.videojuego.id = :idVideojuego")
    List<Imagen> imagenes(@Param("idVideojuego") Long id);

}
