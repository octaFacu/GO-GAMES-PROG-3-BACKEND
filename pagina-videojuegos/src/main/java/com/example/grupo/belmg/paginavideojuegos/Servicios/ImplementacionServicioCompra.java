package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Compra;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionServicioCompra extends ImplementacionServicioBase<Compra,Long> implements ServicioCompra{

    @Autowired
    RepositorioCompra repositorio;
}
