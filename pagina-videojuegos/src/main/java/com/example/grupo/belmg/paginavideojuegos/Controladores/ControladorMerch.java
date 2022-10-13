package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Merch;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioMerch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "gogames/v1/merchs")
public class ControladorMerch extends ImplementacionControladorBase<Merch, ImplementacionServicioMerch>{

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String filtro){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.find(filtro));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage()+"\"}"));
        }
    }

}
