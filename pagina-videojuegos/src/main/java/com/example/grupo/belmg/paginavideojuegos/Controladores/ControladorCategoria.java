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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping(path = "/categoria")
public class ControladorCategoria extends ImplementacionControladorBase<Categoria, ImplementacionServicioCategoria> {

    @Autowired
    private ImplementacionServicioVideojuego servicioVideojuego;

    @Autowired
    private ImplementacionServicioCategoria servicioCategoria;

    @Autowired
    private ImplementacionServicioEstudio servicioEstudio;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String filtro){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.find(filtro));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage()+"\"}"));
        }
    }

    @GetMapping("/inicio")
    public String getAllCategorias(Model model){

        try {

            List<Categoria> categorias = this.servicioCategoria.findAll();
            model.addAttribute("categorias", categorias);

            //Tomar la imagen de portada del ultimo videojuego cargado
            List<Videojuego> videojuegos = this.servicioVideojuego.findAll();
            if(videojuegos.size() > 0){
                Videojuego ultVideojuego = videojuegos.get(videojuegos.size()-1);
                model.addAttribute("ultVideojuego", ultVideojuego);
            }else {
                model.addAttribute("ultVideojuego", null);
            }

            List<Estudio> estudios = this.servicioEstudio.findAll();
            model.addAttribute("estudios", estudios);

        }catch(Exception e){
            return"error";
        }

        return "views/inicio";
    }

    @GetMapping("/categoriaDetalle/{id}")
    public String mostrarJuegosDeCategoria(Model model, @PathVariable("id") Long id, @RequestParam Map<String, Object> params) throws Exception {


        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

        PageRequest pageRequest = PageRequest.of(page,4);

        Page<Videojuego> pageVideojuego = servicioVideojuego.findAllByActivoAndCategoria(id, pageRequest);

        int totalPage = pageVideojuego.getTotalPages();                                                     //Total de paginas que tienen los datos de la base de datos(cuantos links se muestran)
        if(totalPage > 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());   //Se crea un listado de numeros desde el 1 al numero final
            model.addAttribute("pages", pages);
        }

        Categoria categoria = servicioCategoria.findById(id);

        model.addAttribute("categoria", categoria);
        model.addAttribute("videojuegos", pageVideojuego.getContent());
        model.addAttribute("current", page + 1);                                        //Parametro para identificar la pagina actual
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return "views/categoriaDetalle";
    }
}
