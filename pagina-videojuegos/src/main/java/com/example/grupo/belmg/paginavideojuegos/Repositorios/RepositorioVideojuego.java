package com.example.grupo.belmg.paginavideojuegos.Repositorios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioVideojuego extends RepositorioBase<Videojuego, Long>{


    @Query(value = "SELECT * FROM videojuegos WHERE videojuegos.activo = true", nativeQuery = true)
    List<Videojuego> findAllByActivo();

    @Query(value = "SELECT * FROM videojuegos WHERE videojuegos.id = :id AND videojuegos.activo = true", nativeQuery = true)
    Optional<Videojuego> findByIdAndActivo(@Param("id") long id);



    @Query(
            value = "SELECT * FROM videojuegos WHERE videojuegos.nombre LIKE %:filtro%",
            nativeQuery = true
    )
    List<Videojuego> searchNativo(@Param("filtro") String filtro);

    @Query(
            value = "SELECT * FROM videojuegos WHERE videojuegos.nombre LIKE %:filtro%",
            countQuery = "SELECT count(*) FROM videojuegos",
            nativeQuery = true
    )
    Page<Videojuego> searchNativo(@Param("filtro") String filtro, Pageable pageable);

    @Query(value = "SELECT * FROM videojuegos WHERE videojuegos.activo = true",
            nativeQuery = true,
            countQuery = "SELECT count(5) FROM videojuegos")
    Page<Videojuego> findAllByActivoPageable(Pageable pageable);

    @Query(value = "SELECT * FROM videojuegos WHERE videojuegos.activo = true AND videojuegos.fk_categoria = :idCategoria",
            nativeQuery = true,
            countQuery = "SELECT count(3) FROM videojuegos")
    Page<Videojuego> findByActivoAndCategoriaPageable(Pageable pageable, @Param("idCategoria") Long idCategoria);

}
