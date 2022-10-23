package com.example.grupo.belmg.paginavideojuegos.Controladores;


import com.example.grupo.belmg.paginavideojuegos.Entidades.Base;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

//Va a tener todas la operaciones basicas que tiene que realizar nuestro controlador

public interface ControladorBase<E extends Base, ID extends Serializable>{

    public ResponseEntity<?> getAll();
    public ResponseEntity<?> getOne(@PathVariable ID id);
    public ResponseEntity<?> save(@RequestBody E entity);
    public ResponseEntity<?> update(@PathVariable ID id, @RequestBody E entity);
    public ResponseEntity<?> delete(@PathVariable ID id);

    public ResponseEntity<?> getAll(Pageable pageable);

    //public ResponseEntity<?> search(@RequestParam String filtro);

    //public ResponseEntity<?> search(@RequestParam String filtro, Pageable pageable);
}
