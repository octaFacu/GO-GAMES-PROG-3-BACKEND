package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ImplementacionServicioVideojuego extends ImplementacionServicioBase<Videojuego, Long> implements ServicioVideojuego{

    @Autowired
    private RepositorioVideojuego repositorio;

    @Override
    @Transactional
    public List<Videojuego> find(String filtro) throws Exception {
        try{
            //Query con los metodos
            List<Videojuego> videojuegos = repositorio.search(filtro);

            return videojuegos;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /*@Transactional
    public boolean deleteActivoById(long id) throws Exception {
        try{
            Optional<Videojuego> opt = this.repositorio.findById(id);
            if(!opt.isEmpty()){
                Videojuego videojuego = opt.get();
                videojuego.setActivo(!videojuego.isActivo());
                this.repositorio.save(videojuego);
            }else{
                throw new Exception();
            }
            return true;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }*/

    //---

    @Transactional
    public List<Videojuego> findAllByActivo() throws Exception{

        try{
            List<Videojuego> entidades = this.repositorio.findAllByActivo();
            return entidades;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

    }
    @Transactional
    public Videojuego findByIdAndActivo(Long id) throws Exception{

        try{
            Optional<Videojuego> opt = this.repositorio.findByIdAndActivo(id);
            return opt.get();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
