package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Merch;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioMerch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionServicioMerch extends ImplementacionServicioBase<Merch, Long> implements ServicioMerch{

    @Autowired
    private RepositorioMerch repositorio;
}
