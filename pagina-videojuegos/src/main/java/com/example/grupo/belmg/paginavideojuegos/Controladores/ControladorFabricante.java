package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Fabricante;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioFabricante;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "gogames/v1/fabricantes")
public class ControladorFabricante extends ImplementacionControladorBase<Fabricante, ImplementacionServicioFabricante>{
}
