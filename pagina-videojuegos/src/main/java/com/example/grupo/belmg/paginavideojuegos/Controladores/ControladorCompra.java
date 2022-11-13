package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Compra;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Merch;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "compra")
public class ControladorCompra extends ImplementacionControladorBase<Compra, ImplementacionServicioCompra>{

    @Autowired
    ImplementacionServicioUsuario servicioUsuario;

    @Autowired
    ImplementacionServicioVideojuego servicioVideojuego;

    @Autowired
    ImplementacionServicioMerch servicioMerch;

    @GetMapping("/searchCompra/{id}")
    public ResponseEntity<?> searchCompra(@PathVariable Long id){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findCompraByUserId(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage()+"\"}"));
        }
    }


}
