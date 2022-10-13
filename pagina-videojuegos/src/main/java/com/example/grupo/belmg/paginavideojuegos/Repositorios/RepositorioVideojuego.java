package com.example.grupo.belmg.paginavideojuegos.Repositorios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioVideojuego extends RepositorioBase<Videojuego, Long>{

    @Query(value = "SELECT * FROM videojuegos WHERE videojuegos.nombre LIKE %?1%",
            nativeQuery = true
    )
    List<Videojuego> search(String filtro);

}
