package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;

import javax.transaction.Transactional;
import java.util.List;

public interface ServicioVideojuego extends ServicioBase<Videojuego, Long>{


    List<Videojuego> find(String filtro) throws Exception;

}
