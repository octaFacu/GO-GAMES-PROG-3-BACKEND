package com.example.grupo.belmg.paginavideojuegos.Servicios;



import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ServicioUsuario extends ServicioBase<Usuario, Long>, UserDetailsService {
    List<Usuario> find(String filtro) throws Exception;
    public UserDetails loadUserByUsername(String username);
    public long traerIdUsuarioActual(String email)throws Exception;
    public void guardarDireccionYTarjeta(long idUsuario, long idDireccion, long idTarjeta)throws  Exception;
    public List<Usuario> buscarAdmin() throws Exception;
    public Usuario saveAdmin(Usuario entity) throws Exception;
}
