package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;

import java.util.List;

public interface ServicioCategoria extends ServicioBase<Categoria, Long>{
    List<Categoria> find(String filtro) throws Exception;

}
