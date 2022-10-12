package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Direccion;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioDireccion;
import org.springframework.beans.factory.annotation.Autowired;

public class ServicioDireccion   extends ImplementacionServicioBase<Direccion,Long> {

    @Autowired
    RepositorioDireccion repositorio;

}
