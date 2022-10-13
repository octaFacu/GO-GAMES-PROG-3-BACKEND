package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Estudio;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioEstudio;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "gogames/v1/estudios")
public class ControladorEstudio extends ImplementacionControladorBase<Estudio, ImplementacionServicioEstudio>{
}
