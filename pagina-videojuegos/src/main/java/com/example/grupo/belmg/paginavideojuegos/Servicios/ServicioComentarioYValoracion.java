package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Comentarios_Valoracion;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioComentarioYVal;
import org.springframework.beans.factory.annotation.Autowired;

public class ServicioComentarioYValoracion  extends ImplementacionServicioBase<Comentarios_Valoracion,Long> {

    @Autowired
    RepositorioComentarioYVal repositorio;


}