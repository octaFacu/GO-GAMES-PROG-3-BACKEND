package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Compra;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioCompra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "gogames/v1/compras")
public class ControladorCompra extends ImplementacionControladorBase<Compra, ImplementacionServicioCompra>{


    @GetMapping("/searchCompra/{id}")
    public ResponseEntity<?> searchCompra(@PathVariable Long id){

        try{
            //System.out.println("id: " + id);
            return ResponseEntity.status(HttpStatus.OK).body(service.findCompraByUserId(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage()+"\"}"));
        }
    }

}
