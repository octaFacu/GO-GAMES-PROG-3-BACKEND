package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Videojuego> search(String filtro) throws Exception {

        try{

            List<Videojuego> videojuegos = repositorio.searchNativo(filtro);
            return videojuegos;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public Page<Videojuego> search(String filtro, Pageable pageable) throws Exception {

        try{

            Page<Videojuego> videojuegos = repositorio.searchNativo(filtro, pageable);
            return videojuegos;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public Page<Videojuego> getAll(Pageable pageable){

        return repositorio.findAll(pageable);

    }

    @Transactional
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
    }



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
    public Page<Videojuego> findAllByActivoPageable(Pageable pageable) throws Exception {

        try {
            Page<Videojuego> videojuegos = this.repositorio.findAllByActivoPageable(pageable);
            return videojuegos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Transactional
    public Page<Videojuego> findByActivoAndCategoriaPageable(Pageable pageable, Long idCategoria) throws Exception {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        try {
            Page<Videojuego> videojuegos = this.repositorio.findByActivoAndCategoriaPageable(pageable, idCategoria);
            return videojuegos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
    //PRUEBAAAAAAAAS
    @Override
    public Page<Videojuego> findPaginated(int pageNo, int pageSize, Long idCategoria) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.repositorio.findByActivoAndCategoriaPageable(pageable, idCategoria);
    }


}
