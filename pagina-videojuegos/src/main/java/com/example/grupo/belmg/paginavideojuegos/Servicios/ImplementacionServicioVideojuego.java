package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

}
