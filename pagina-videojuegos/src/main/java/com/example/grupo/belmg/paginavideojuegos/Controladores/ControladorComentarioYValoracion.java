package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Comentarios_Valoracion;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioComentarioYValoracion;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "gogames/v1/comentarios")
public class ControladorComentarioYValoracion extends ImplementacionControladorBase<Comentarios_Valoracion, ImplementacionServicioComentarioYValoracion>{
}
