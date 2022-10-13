package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Compra;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioCompra;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "gogames/v1/compras")
public class ControladorCompra extends ImplementacionControladorBase<Compra, ImplementacionServicioCompra>{
}
