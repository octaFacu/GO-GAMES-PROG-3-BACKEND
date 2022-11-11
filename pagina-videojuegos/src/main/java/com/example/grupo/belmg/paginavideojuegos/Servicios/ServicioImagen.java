package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Imagen;

import java.util.List;

public interface ServicioImagen extends ServicioBase<Imagen, Long>{

    List<Imagen> findImagenByVideojuegoId(Long id) throws Exception;

    List<Imagen> findImagenByMerchId(Long id) throws Exception;
}