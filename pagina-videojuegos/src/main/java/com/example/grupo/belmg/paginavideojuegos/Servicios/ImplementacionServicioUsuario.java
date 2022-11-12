package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Rol;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    @Transactional
    public Usuario save(Usuario entity) throws Exception {

        try{
            if(repositorio.existsById(id)){
                entity.setContrasenia(BCrypt.hashpw(entity.getContrasenia(),BCrypt.gensalt()));
                entity.setRol(Arrays.asList(new Rol("ROLE_USER")));
                entity = repositorio.save(entity);
                return entity;
            }else{
                entity.setContrasenia(BCrypt.hashpw(entity.getContrasenia(),BCrypt.gensalt()));
                entity.setRol(Arrays.asList(new Rol("ROLE_SUPERADMIN")));
                entity = repositorio.save(entity);
                return entity;
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Usuario saveAdmin(Usuario entity) throws Exception{
        entity.setContrasenia(BCrypt.hashpw(entity.getContrasenia(),BCrypt.gensalt()));
        entity.setAdmin(true);
        entity.setRol(Arrays.asList(new Rol("ROLE_ADMIN")));
        entity = repositorio.save(entity);
        return entity;
    }
    @Override
    public List<Usuario> buscarAdmin() throws Exception{
        try {
            List<Usuario> entity = repositorio.buscaAdmin();
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

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

    @Override
    public long traerIdUsuarioActual(String email)throws Exception{
        try {
            long id = this.repositorio.traerIdUsuarioActual(email);
            return id;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public void guardarDireccionYTarjeta(long idUsuario, long idDireccion, long idTarjeta)throws  Exception{
        try {
            repositorio.guardarDireccionYTarjeta(idUsuario,idDireccion,idTarjeta);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //nos carga un usuario con sus datos
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repositorio.findByEmail(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario o contraseña incorectar");
        }
        return new User(usuario.getEmail(),usuario.getContrasenia(), mapearRoles(usuario.getRol()));

    }

    public long obtenerUsuario() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        long idU;
        return idU = traerIdUsuarioActual(email);
    }
    //aca lo que hacemos es mapear los roles a autoridades para poder retornarselo a loadUserByUsername
    private Collection<? extends GrantedAuthority> mapearRoles(Collection<Rol>roles){

        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
    }
}
