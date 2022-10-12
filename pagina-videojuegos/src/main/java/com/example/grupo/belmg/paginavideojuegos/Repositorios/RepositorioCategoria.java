package com.example.grupo.belmg.paginavideojuegos.Repositorios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioCategoria extends RepositorioBase<Categoria, Long> {
    List<Categoria> findByNombre(String tipo);

    @Query(value = "SELECT * FROM Categoria WHERE categoria.tipo LIKE %?1%",
            nativeQuery = true
    )
    List<Categoria> search(String filtro);
}
