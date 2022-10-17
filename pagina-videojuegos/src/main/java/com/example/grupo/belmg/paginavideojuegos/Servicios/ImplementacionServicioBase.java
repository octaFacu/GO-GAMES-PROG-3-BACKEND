package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Base;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class ImplementacionServicioBase<E extends Base, ID extends Serializable> implements ServicioBase<E,ID> {

    @Autowired
    protected RepositorioBase<E, ID> repositorio;

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try {
            List<E> entidades = repositorio.findAll();
            return entidades;
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try{
            Optional<E> entidad = repositorio.findById(id);
            return entidad.get();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {

        try{
            entity = repositorio.save(entity);
            return entity;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {

        try{
            Optional<E> entidad = repositorio.findById(id);
            E entidadActualizar = entidad.get();
            entidadActualizar = repositorio.save(entity);
            return entidadActualizar;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {

        try{
            if(repositorio.existsById(id)) {

                repositorio.deleteById(id);
                return true;
            }else {

                throw new Exception();
            }

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
