package com.example.grupo.belmg.paginavideojuegos.Controllers;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public abstract class BaseControllerImplementation  <E extends Base, S extends BaseServiceImplementation<E, Long>> implements BaseController<E, Long>{

    @Autowired
    protected S service;

    //Hay que pasa la hora especifica en que se llamo a este
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
            //Devuelve un estado http si encuentra
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

    @GetMapping("/paged")
    public ResponseEntity<?> getAll(Pageable pageable){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));

        }catch(Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, intente mas tarde.\"}");
        }
    }

}

