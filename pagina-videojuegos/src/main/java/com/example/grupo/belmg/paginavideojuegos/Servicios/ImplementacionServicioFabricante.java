package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Fabricante;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioFabricante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionServicioFabricante extends ImplementacionServicioBase<Fabricante, Long> implements ServicioFabricante{

    @Autowired
    private RepositorioFabricante repositorio;
}
