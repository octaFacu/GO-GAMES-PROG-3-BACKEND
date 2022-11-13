package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Rol;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplementacionServicioUsuario extends ImplementacionServicioBase<Usuario,Long> implements ServicioUsuario {

    long id=1;

    @Autowired
    RepositorioUsuario repositorio;

    @Override
    public List<Usuario> find(String filtro) throws Exception {
        try{

            List<Usuario> usuarios = repositorio.search(filtro);
            return usuarios;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public long traerIdUsuarioActual(String email) throws Exception {
        return 0;
    }

    @Override
    public void guardarDireccionYTarjeta(long idUsuario, long idDireccion, long idTarjeta) throws Exception {

    }

    @Override
    public List<Usuario> buscarAdmin() throws Exception {
        List<Usuario> usuarios = repositorio.buscaAdmin();
        return usuarios;
    }

    @Override
    public Usuario saveAdmin(Usuario entity) throws Exception {
        return null;
    }
}
