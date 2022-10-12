package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplementacionServicioCategoria extends ImplementacionServicioBase<Categoria, Long> implements ServicioCategoria{

    @Autowired
    private RepositorioCategoria repositorioCategoria;

    @Override
    public List<Categoria> find(String filtro) throws Exception {
        try{
            //Query con los metodos
            List<Categoria> categorias = repositorioCategoria.search(filtro);

            return categorias;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
