package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Compra;

import java.util.List;

public interface ServicioCompra extends ServicioBase<Compra, Long>{

    List<Compra> findCompraByUserId(Long id) throws Exception;
    List<Compra> idvideojuegosComprados(Long idUsuaio) throws Exception;
}
