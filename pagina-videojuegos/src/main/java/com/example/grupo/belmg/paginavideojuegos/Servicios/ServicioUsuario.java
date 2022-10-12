package com.example.grupo.belmg.paginavideojuegos.Servicios;



import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;

import java.util.List;

public interface ServicioUsuario extends ServicioBase<Usuario, Long>{
    List<Usuario> find(String filtro) throws Exception;
}
