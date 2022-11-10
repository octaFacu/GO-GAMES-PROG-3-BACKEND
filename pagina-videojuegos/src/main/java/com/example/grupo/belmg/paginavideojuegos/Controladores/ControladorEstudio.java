package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Estudio;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioCategoria;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioEstudio;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/estudio")
public class ControladorEstudio extends ImplementacionControladorBase<Estudio, ImplementacionServicioEstudio>{

    @Autowired
    private ImplementacionServicioVideojuego servicioVideojuego;


    @Autowired
    private ImplementacionServicioEstudio servicioEstudio;


    @GetMapping("/estudioDetalle/{id}")
    public String mostrarJuegosDeEstudio(Model model, @PathVariable("id") Long id, @RequestParam Map<String, Object> params) throws Exception {


        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

        PageRequest pageRequest = PageRequest.of(page,4);

        Page<Videojuego> pageVideojuego = servicioVideojuego.findAllByActivoAndEstudio(id, pageRequest);

        int totalPage = pageVideojuego.getTotalPages();                                                     //Total de paginas que tienen los datos de la base de datos(cuantos links se muestran)
        if(totalPage > 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());   //Se crea un listado de numeros desde el 1 al numero final
            model.addAttribute("pages", pages);
        }

        Estudio estudio = servicioEstudio.findById(id);

        model.addAttribute("estudio", estudio);
        model.addAttribute("videojuegos", pageVideojuego.getContent());
        model.addAttribute("current", page + 1);                                        //Parametro para identificar la pagina actual
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return "views/estudioDetalle";
    }

}
