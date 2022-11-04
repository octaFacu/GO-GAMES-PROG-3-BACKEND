package com.example.grupo.belmg.paginavideojuegos.Servicios;



import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ServicioUsuario extends ServicioBase<Usuario, Long>, UserDetailsService {
    List<Usuario> find(String filtro) throws Exception;
    public UserDetails loadUserByUsername(String username);
}
