package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Direccion;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioDireccion;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "gogames/v1/direccion")
public class ControladorDireccion extends ImplementacionControladorBase<Direccion, ImplementacionServicioDireccion>{

}
