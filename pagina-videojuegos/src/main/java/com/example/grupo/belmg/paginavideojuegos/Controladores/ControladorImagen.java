package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Imagen;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioImagen;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "imagen")
public class ControladorImagen extends ImplementacionControladorBase<Imagen, ImplementacionServicioImagen>{

    @GetMapping("/searchImagenByVideojuego/{id}")
    public ResponseEntity<?> findImagenByVideojuegoId(@PathVariable Long id){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(service.findImagenByVideojuegoId(id));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }

    }

    @GetMapping("/searchImagenByMerch/{id}")
    public ResponseEntity<?> findImagenByMerchId(@PathVariable Long id){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(service.findImagenByMerchId(id));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }

    }

}
