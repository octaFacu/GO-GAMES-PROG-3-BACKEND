package com.example.grupo.belmg.paginavideojuegos.Controladores;


import com.example.grupo.belmg.paginavideojuegos.Entidades.DetallesTarjeta;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioDetallesTarjeta;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "gogames/v1/detalles_terjetas")
public class ControladorDetallesTarjeta extends ImplementacionControladorBase<DetallesTarjeta, ImplementacionServicioDetallesTarjeta>{
}
