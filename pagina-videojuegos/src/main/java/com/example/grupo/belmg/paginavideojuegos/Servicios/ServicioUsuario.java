package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/*public class ServicioUsuario  implements ServicioBaseImplementacion{


    @Autowired
    RepositorioUsuario repositorio;


    @Transactional
    public Usuario saveOneUsuario(Usuario entity) throws Exception {
        try {
            Usuario usuario = this.repositorio.save(entity);
            //usuario.setContraseña(encoder.encode(entity.getContraseña()));
            return usuario;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }





}
