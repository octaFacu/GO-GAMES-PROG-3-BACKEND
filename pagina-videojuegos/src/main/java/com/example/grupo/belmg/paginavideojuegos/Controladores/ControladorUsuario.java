package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.*;
import com.example.grupo.belmg.paginavideojuegos.Servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "usuarios")
public class ControladorUsuario extends ImplementacionControladorBase<Usuario, ImplementacionServicioUsuario>{
    static int i = 0;
    static String guardaAnterior = null;
    @Autowired
    ImplementacionServicioDireccion servicioDireccion;

    @Autowired
    ImplementacionServicioDetallesTarjeta servicioTarjeta;

    @Autowired
    ImplementacionServicioCompra servicioCompra;

    @Autowired
    ImplementacionServicioVideojuego servicioVideojuego;

    @Autowired
    ImplementacionServicioMerch servicioMerch;
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String filtro){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.find(filtro));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage()+"\"}"));
        }
    }


}
