package com.example.grupo.belmg.paginavideojuegos.Repositorios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Merch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioMerch extends RepositorioBase<Merch, Long>{

    @Query(value = "SELECT * FROM merchs WHERE merchs.nombre LIKE %?1%",
            nativeQuery = true
    )
    List<Merch> search(String filtro);

}
