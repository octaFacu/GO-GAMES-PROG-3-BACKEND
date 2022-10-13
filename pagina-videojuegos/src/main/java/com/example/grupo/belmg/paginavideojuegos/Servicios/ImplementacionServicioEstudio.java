package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Estudio;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioEstudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionServicioEstudio extends ImplementacionServicioBase<Estudio,Long> implements ServicioEstudio{

    @Autowired
    RepositorioEstudio repositorio;
}
