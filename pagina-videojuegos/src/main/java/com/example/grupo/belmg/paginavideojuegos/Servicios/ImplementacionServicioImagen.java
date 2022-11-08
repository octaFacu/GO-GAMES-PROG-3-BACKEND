package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Imagen;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioImagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ImplementacionServicioImagen extends ImplementacionServicioBase<Imagen,Long> implements ServicioImagen{

    @Autowired
    RepositorioImagen repositorio;

    @Override
    @Transactional
    public List<Imagen> findImagenByVideojuegoId(Long id) throws Exception {
        try{
            //Query con los metodos
            List<Imagen> imagenes = repositorio.imagenes(id);

            return imagenes;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
