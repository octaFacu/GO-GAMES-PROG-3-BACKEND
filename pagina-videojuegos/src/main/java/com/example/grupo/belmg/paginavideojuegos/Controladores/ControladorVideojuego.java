package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioVideojuego;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "gogames/v1/videojuegos")
public class ControladorVideojuego extends ImplementacionControladorBase<Videojuego, ImplementacionServicioVideojuego>{
}
