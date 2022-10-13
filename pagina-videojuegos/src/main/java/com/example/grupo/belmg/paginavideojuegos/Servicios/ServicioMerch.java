package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Merch;

import java.util.List;

public interface ServicioMerch extends ServicioBase<Merch, Long>{

    List<Merch> find(String filtro) throws Exception;
}
