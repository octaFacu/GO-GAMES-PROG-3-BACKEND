package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Compra;
import com.example.grupo.belmg.paginavideojuegos.Repositorios.RepositorioCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ImplementacionServicioCompra extends ImplementacionServicioBase<Compra,Long> implements ServicioCompra{

    @Autowired
    RepositorioCompra repositorio;


    @Override
    @Transactional
    public List<Compra> findCompraByUserId(Long id) throws Exception {
        try{
            //Query con los metodos
            List<Compra> compras = repositorio.compras(id);

            return compras;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Compra> idvideojuegosComprados(Long idUsuaio) throws Exception {
        try {
            List<Compra> comprasDeUsuario = repositorio.comprasUsuario(idUsuaio);
            return comprasDeUsuario;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
