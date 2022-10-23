package com.example.grupo.belmg.paginavideojuegos.Servicios;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Merch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServicioMerch extends ServicioBase<Merch, Long>{

    public List<Merch> search(String filtro) throws Exception;

    public Page<Merch> search(String filtro, Pageable pageable) throws Exception;
}
