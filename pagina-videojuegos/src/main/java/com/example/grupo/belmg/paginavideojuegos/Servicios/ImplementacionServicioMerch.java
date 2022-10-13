package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Merch;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioMerch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplementacionServicioMerch extends ImplementacionServicioBase<Merch, Long> implements ServicioMerch{

    @Autowired
    private RepositorioMerch repositorio;


    @Override
    public List<Merch> find(String filtro) throws Exception {
        try{
            //Query con los metodos
            List<Merch> merchs = repositorio.search(filtro);

            return merchs;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
