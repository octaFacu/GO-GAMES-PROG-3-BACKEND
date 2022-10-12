package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Direccion;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioDireccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionServicioDireccion extends ImplementacionServicioBase<Direccion,Long> implements ServicioDireccion{

    @Autowired
    RepositorioDireccion repositorio;

}
