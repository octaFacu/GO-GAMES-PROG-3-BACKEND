package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Merch;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioMerch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ImplementacionServicioMerch extends ImplementacionServicioBase<Merch, Long> implements ServicioMerch{

    @Autowired
    private RepositorioMerch repositorio;


    @Override
    @Transactional
    public List<Merch> search(String filtro) throws Exception {

        try{

            List<Merch> merchs = repositorio.searchNativo(filtro);
            return merchs;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public Page<Merch> search(String filtro, Pageable pageable) throws Exception {

        try{

            Page<Merch> merchs = repositorio.searchNativo(filtro, pageable);
            return merchs;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

    }


    /*@Transactional
    public boolean deleteById(long id) throws Exception {
        try{
            Optional<Merch> opt = this.repositorio.findById(id);
            if(!opt.isEmpty()){
                Merch merch = opt.get();
                merch.setActivo(!merch.isActivo());
                this.repositorio.save(merch);
            }else{
                throw new Exception();
            }
            return true;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }*/

    //----

    @Transactional
    public List<Merch> findAllByActivo() throws Exception{

        try{
            List<Merch> entidades = this.repositorio.findAllByActivo();
            return entidades;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

    }
    @Transactional
    public Merch findByIdAndActivo(Long id) throws Exception{

        try{
            Optional<Merch> opt = this.repositorio.findByIdAndActivo(id);
            return opt.get();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Page<Merch> findByNombre(String q, Pageable pageable) throws Exception{
        try{
            Page<Merch> entities = this.repositorio.findByNombre(q, pageable);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
