package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionServicioVideojuego extends ImplementacionServicioBase<Videojuego, Long> implements ServicioVideojuego{

    @Autowired
    private RepositorioVideojuego repositorio;

}
