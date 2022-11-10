package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

public interface ServicioVideojuego extends ServicioBase<Videojuego, Long>{


    public List<Videojuego> search(String filtro) throws Exception;

    public Page<Videojuego> search(String filtro, Pageable pageable) throws Exception;


    public Page<Videojuego> getAll(Pageable pageable);

    public Page<Videojuego> findAllByActivoPageable(Pageable pageable) throws Exception;

    public Page<Videojuego> findByActivoAndCategoriaPageable(Pageable pageable, Long idCategoria) throws Exception;


    public Page<Videojuego> findAllByActivoAndCategoria(Long idCategoria, Pageable pageable) throws Exception;

    public Page<Videojuego> findAllByActivoAndEstudio(Long idCategoria, Pageable pageable) throws Exception;

}
