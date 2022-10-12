package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ImplementacionServicioUsuario extends ImplementacionServicioBase<Usuario,Long> implements ServicioUsuario{


    @Autowired
    RepositorioUsuario repositorio;


    @Override
    public List<Usuario> find(String filtro) throws Exception {
        try{
            //Query con los metodos
            List<Usuario> usuarios = repositorio.search(filtro);

            return usuarios;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
