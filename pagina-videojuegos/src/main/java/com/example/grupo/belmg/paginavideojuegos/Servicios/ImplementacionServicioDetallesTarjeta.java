package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.DetallesTarjeta;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioDetallesTarjeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionServicioDetallesTarjeta extends ImplementacionServicioBase<DetallesTarjeta,Long> implements ServicioDetallesTarjeta{

    @Autowired
    RepositorioDetallesTarjeta repositorio;
}
