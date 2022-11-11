package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.*;

import com.example.grupo.belmg.paginavideojuegos.Servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@RestController
//@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/videojuego")

public class ControladorVideojuego extends ImplementacionControladorBase<Videojuego, ImplementacionServicioVideojuego>{

    @Autowired
    private ImplementacionServicioVideojuego servicioVideojuego;

    @Autowired
    private ImplementacionServicioCategoria servicioCategoria;

    @Autowired
    private ImplementacionServicioEstudio servicioEstudio;

    @Autowired
    private ImplementacionServicioImagen servicioImagen;

    @Autowired
    private ImplementacionServicioComentarioYValoracion servicioComentarioYValoracion;

    @Autowired
    private ImplementacionServicioUsuario servicioUsuario;


    //----------CRUD------------

    @GetMapping("/crudVideojuego")
    public String findAll(@RequestParam Map<String, Object> params, Model model){

        try{

            int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

            PageRequest pageRequest = PageRequest.of(page,5);

            Page<Videojuego> pageVideojuego = servicioVideojuego.getAll(pageRequest);

            int totalPage = pageVideojuego.getTotalPages();                                                     //Total de paginas que tienen los datos de la base de datos(cuantos links se muestran)
            if(totalPage > 0){
                List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());   //Se crea un listado de numeros desde el 1 al numero final
                model.addAttribute("pages", pages);
            }

            model.addAttribute("videojuegos", pageVideojuego.getContent());
            model.addAttribute("current", page + 1);                                        //Parametro para identificar la pagina actual
            model.addAttribute("next", page + 2);
            model.addAttribute("prev", page);
            model.addAttribute("last", totalPage);

            return "views/crudVideojuego";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/formulario/videojuego/{id}")
    public String formularioVideojuego(Model model, @PathVariable("id") long id){

        try{
            model.addAttribute("categorias", this.servicioCategoria.findAll());
            model.addAttribute("estudios", this.servicioEstudio.findAll());
            if(id == 0){
                model.addAttribute("videojuego", new Videojuego());
            }else{
                model.addAttribute("videojuego", this.servicioVideojuego.findById(id));
            }

            return "views/formulario/videojuego";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/formulario/videojuego/{id}")
    public String guardarVideojuego(@Valid @ModelAttribute("videojuego") Videojuego videojuego, BindingResult result, Model model, @PathVariable("id") long id){

        try{

            model.addAttribute("categorias", this.servicioCategoria.findAll());
            model.addAttribute("estudios", this.servicioEstudio.findAll());

            if(result.hasErrors()){
                return "views/formulario/videojuego";
            }

            if(id == 0){
                this.servicioVideojuego.save(videojuego);
            }else{
                this.servicioVideojuego.update(id, videojuego);
            }

            return "redirect:/videojuego/crudVideojuego";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    @GetMapping("/eliminar/videojuego/{id}")
    public String eliminarVideojuego(Model model, @PathVariable("id") long id){

        try{

            model.addAttribute("videojuego", this.servicioVideojuego.findById(id));
            return "views/formulario/eliminar";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/eliminar/videojuego/{id}")
    public String desactivarVideojuego(Model model, @PathVariable("id") long id){

        try{
            this.servicioVideojuego.deleteActivoById(id);
            return "redirect:/videojuego/crudVideojuego";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    //----------------- IMAGENES VIDEOJUEGO CRUD ----------------

    @GetMapping("/ingresoimg/videojuego/{id}")
    public String ingresoimgVideojuego(Model model, @PathVariable("id") long id){

        try{

            int cantImagenes = this.servicioImagen.findImagenByVideojuegoId(id).size();

            model.addAttribute("videojuego", this.servicioVideojuego.findById(id));
            model.addAttribute("imagenes", this.servicioImagen.findImagenByVideojuegoId(id));
            model.addAttribute("imagen", new Imagen());
            model.addAttribute("cantImagenes", cantImagenes);

            return "views/formulario/ingresoimg";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/postingresoimg/videojuego/{id}")
    public String finingresoimgVideojuego(@ModelAttribute("imagen") Imagen imagen, Model model, @PathVariable("id") long id) {

        try {

            model.addAttribute("videojuego", this.servicioVideojuego.findById(id));
            model.addAttribute("imagenes", this.servicioImagen.findImagenByVideojuegoId(id));

            imagen.setVideojuego(this.servicioVideojuego.findById(id));
            servicioImagen.save(imagen);

            return "redirect:/videojuego/ingresoimg/videojuego/{id}";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    @PostMapping("/inicioeliminarimg/videojuego/{idVideojuego}")
    public String inicioEliminaimgimgVideojuego(@ModelAttribute("imagen") Imagen imagen, @PathVariable("idVideojuego") long idVideojuego, Model model){

        try{

            long imagenid = imagen.getId();

            Imagen img = this.servicioImagen.findById(imagenid);
            String imagenlink = img.getLink();
            //System.out.println("ID IMAGEN: " + imagenid);
            //System.out.println("LINK IMAGEN: " + imagenlink);

            model.addAttribute("videojuego", this.servicioVideojuego.findById(idVideojuego));
            model.addAttribute("imagenes", this.servicioImagen.findImagenByVideojuegoId(idVideojuego));
            model.addAttribute("imagenid", imagenid);
            model.addAttribute("imagenlink", imagenlink);

            return "views/formulario/eliminarimg";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/posteliminarimg/videojuego/{idVideojuego}/{id}")
    public String postEliminaimgVideojuego(@PathVariable("idVideojuego") long idVideojuego,@PathVariable("id") long id, Model model){

        try{

            this.servicioImagen.delete(id);

            return "redirect:/videojuego/ingresoimg/videojuego/{idVideojuego}";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    //----------------- FIN IMAGENES VIDEOJUEGO CRUD ----------------


    //-------------FIN CRUD------------------



    //videojuego/detalle/id
    @GetMapping("/detalle/{id}")
    public String detalleVideojuego(Model model, @PathVariable("id") long id) {
        try {
            Videojuego videojuego = this.servicioVideojuego.findById(id);
            Categoria categoria = videojuego.getCategoria();
            Estudio estudio = videojuego.getEstudio();

            List<Imagen> imagenes = this.servicioImagen.findImagenByVideojuegoId(id);

            List<Comentarios_Valoracion> comentValo = videojuego.getComentarios_valoraciones();

            model.addAttribute("comentarioNuevo", new Comentarios_Valoracion());

            model.addAttribute("videojuego",videojuego);
            model.addAttribute("categoria", categoria);
            model.addAttribute("estudio", estudio);
            model.addAttribute("imagenes", imagenes);
            model.addAttribute("comentarios", comentValo);

            return "views/detalle";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }




    @PostMapping("/detalle/{idVideojuego}")
    public String guardarVideojuego(@Valid @ModelAttribute("comentarioNuevo") Comentarios_Valoracion comentarioNuevo, BindingResult result, Model model, @PathVariable("idVideojuego") long idVideojuego){

        try{


            if(result.hasErrors()){
                return "views/detalle/{idVideojuego}";
            }

                Comentarios_Valoracion comentario = this.servicioComentarioYValoracion.save(comentarioNuevo);

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String email = authentication.getName();


                long idUser = this.servicioUsuario.traerIdUsuarioActual(email);
                comentario.setUsuario(this.servicioUsuario.findById(idUser));

                Videojuego videojuego = this.servicioVideojuego.findById(idVideojuego);

                if(videojuego.getComentarios_valoraciones() == null){
                    List<Comentarios_Valoracion> comments = new ArrayList<>();
                    comments.add(comentario);
                    videojuego.setComentarios_valoraciones(comments);
                }else{
                    List<Comentarios_Valoracion> comments = videojuego.getComentarios_valoraciones();
                    comments.add(comentario);
                    videojuego.setComentarios_valoraciones(comments);
                }
                this.servicioVideojuego.update(idVideojuego,videojuego);



            return "redirect:/videojuego/detalle/{idVideojuego}";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }








    @GetMapping(value = "/busqueda")
    public String busquedaVideojuego(Model model, @RequestParam(value ="query",required = false)String q, @RequestParam Map<String, Object> params){
        try {

            int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

            PageRequest pageRequest = PageRequest.of(page,5);

            Page<Videojuego> pageVideojuegos = this.servicioVideojuego.findByNombre(q, pageRequest);

            int totalPage = pageVideojuegos.getTotalPages();                                                     //Total de paginas que tienen los datos de la base de datos(cuantos links se muestran)
            if(totalPage > 0){
                List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());   //Se crea un listado de numeros desde el 1 al numero final
                model.addAttribute("pages", pages);
            }

            model.addAttribute("videojuegos", pageVideojuegos.getContent());
            model.addAttribute("current", page + 1);                                        //Parametro para identificar la pagina actual
            model.addAttribute("next", page + 2);
            model.addAttribute("prev", page);
            model.addAttribute("last", totalPage);
            model.addAttribute("resultado",q);

            return "views/busqueda";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

}
