package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Base;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioBase;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ServicioBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class ImplementacionControladorBase<E extends Base,
        S extends ImplementacionServicioBase<E, Long>> implements ControladorBase<E, Long> {

    @Autowired
    protected S service;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, intente mas tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, intente mas tarde.\"}");
        }
    }


    @PostMapping("")

    public ResponseEntity<?> save(@RequestBody E entity){
        try {
            //Devuelve un estado http si puede guardar a la nueva persona
            return ResponseEntity.status(HttpStatus.OK).body(service.save(entity));

        }catch(Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, intente mas tarde.\"}");
        }

    }


    //Declarar tipo de request
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody E entity){

        try {
            //Devuelve un estado http si encuentra a la persona
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));

        }catch(Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, intente mas tarde.\"}");
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        try {
            //Devuelve un estado http si encuentra
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));

        }catch(Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, intente mas tarde.\"}");
        }

    }



}

