package com.example.grupo.belmg.paginavideojuegos.Repositorios;


import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioUsuario extends RepositorioBase<Usuario, Long>{

    List<Usuario> findByNombre(String tipo);

    @Query(value = "SELECT * FROM Usuario WHERE usuario.nombre = %?1%",
            nativeQuery = true
    )
    List<Usuario> search(String filtro);

    public Usuario findByEmail(String email);

    @Query(value = "SELECT * FROM usuarios WHERE usuarios.admin = TRUE", nativeQuery = true)
    List<Usuario> buscaAdmin();

    @Query(value = "SELECT id FROM usuarios WHERE usuarios.email = ?1",
            nativeQuery = true)
    long traerIdUsuarioActual(String email);

    @Modifying
    @Query(value = "UPDATE usuarios SET fk_tarjeta = ?2,fk_direccion = ?3 WHERE id = ?1",
            nativeQuery = true)
    void guardarDireccionYTarjeta(long idUsuario, long idTarjeta, long idDireccion);
}
