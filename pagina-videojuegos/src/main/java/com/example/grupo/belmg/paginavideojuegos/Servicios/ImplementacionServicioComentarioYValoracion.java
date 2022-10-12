package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Comentarios_Valoracion;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioComentarioYVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionServicioComentarioYValoracion extends ImplementacionServicioBase<Comentarios_Valoracion,Long> implements ServicioComentarioYvaloracion{

    @Autowired
    RepositorioComentarioYVal repositorio;


}