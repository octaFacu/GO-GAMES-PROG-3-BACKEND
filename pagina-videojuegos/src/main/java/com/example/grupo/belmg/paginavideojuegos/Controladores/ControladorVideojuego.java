package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Categoria;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Estudio;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Imagen;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioCategoria;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioEstudio;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioImagen;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
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

    //----------CRUD------------

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


    @GetMapping("/ingresoimg/videojuego/{id}")
    public String ingresoimgVideojuego(Model model, @PathVariable("id") long id){

        try{

            model.addAttribute("videojuego", this.servicioVideojuego.findById(id));
            model.addAttribute("imagenes", this.servicioImagen.findImagenByVideojuegoId(id));

            return "views/formulario/ingresoimg";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    /*@PostMapping("/ingresoimg/videojuego/{id}")
    public String finingresoimgVideojuego(@ModelAttribute("imagen") Imagen imagen, Model model, @PathVariable("id") long id){

        try{
            System.out.println("TRAZAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            model.addAttribute("videojuego", this.servicioVideojuego.findById(id));
            //model.addAttribute("imagen", new Imagen());
            //model.addAttribute("imagenes", this.servicioImagen.findImagenByVideojuegoId(id));

            imagen.setVideojuego(this.servicioVideojuego.findById(id));
            this.servicioImagen.save(imagen);

            return "views/formulario/ingresoimg";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }*/

    @PostMapping("/postingresoimg/videojuego/{id}")
    public String finingresoimgVideojuego(@RequestParam(), BindingResult result,Model model, @PathVariable("id") long id){

        try{

            System.out.println("TRAZAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            //model.addAttribute("videojuego", this.servicioVideojuego.findById(id));
            //model.addAttribute("imagenes", this.servicioImagen.findImagenByVideojuegoId(id));

            imagen.setVideojuego(this.servicioVideojuego.findById(id));
            this.servicioImagen.save(imagen);

            return "views/formulario/ingresoimg";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    //-------------FIN CRUD------------------




    /*@GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String filtro){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(service.search(filtro));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }

    }

    @GetMapping("/searchPaged")
    public ResponseEntity<?> search(@RequestParam String filtro, Pageable pageable){

        try{

            return ResponseEntity.status(HttpStatus.OK).body(service.search(filtro, pageable));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }

    }

    //-----------

    @GetMapping("/searchActivo")
    public ResponseEntity<?> getAllActivo(){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllByActivo());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage()+"\"}"));
        }
    }

    @GetMapping("/searchOneActivo/{id}")
    public ResponseEntity<?> getOneByIdAndActivo(@PathVariable Long id){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findByIdAndActivo(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage()+"\"}"));
        }
    }

    /*@PostMapping("/deleteOneActivo/{id}")
    public ResponseEntity<?> deleteActivoById(@PathVariable Long id){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.deleteActivoById(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage()+"\"}"));
        }
    }*/


    public String busqueda(Model modelo, Pageable pageable){
        try{
            //Page<Videojuego> videojuegos = this.service.findAllByActivoPageable(pageable);

            //modelo.addAttribute("videojuegos", videojuegos);

            return "views/busqueda"; //CAMBIAR A UNA VISTA DE BUSQUEDA
        }catch(Exception e){
            return "";
        }
    }

    @GetMapping("/inicio/A")
    public String inicio(@ModelAttribute Videojuego videojuego, Model model, Pageable pageable){
        try{
            Long idCategoria = Long.valueOf(1);
            //Me trae los juegos de la categoria 0
            //Page<Videojuego> videojuegos = this.service.findByActivoAndCategoriaPageable(pageable, idCategoria);
            List<Videojuego> videojuegos = this.servicioVideojuego.findAllByActivo();


            model.addAttribute("videojuegos", videojuegos);

            return "views/inicio";
        }catch(Exception e){
            return "error";
        }
    }



    //PRUEBAAAAAAAAAS
    @GetMapping("/inicio/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 2;
        Long idCat = 1L;
        Page <Videojuego> page = servicioVideojuego.findPaginated(pageNo, pageSize, idCat);
        List <Videojuego> listaVideojuegos = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listaVideojuegos", listaVideojuegos);
        return "inicio";
    }

    // display list of ¡videojuegos PRUEBAAAA
    @GetMapping("/inicio")
    public String viewHomePage(Model model) {
        return findPaginated(1, model);
    }

    //videojuego/detalle/id
    @GetMapping("/detalle/{id}")
    public String detalleVideojuego(Model model, @PathVariable("id") Long id) {
        try {
            Videojuego videojuego = this.servicioVideojuego.findById(id);
            Categoria categoria = videojuego.getCategoria();
            Estudio estudio = videojuego.getEstudio();

            List<Imagen> imagenes = this.servicioImagen.findImagenByVideojuegoId(id);

            model.addAttribute("videojuego",videojuego);
            model.addAttribute("categoria", categoria);
            model.addAttribute("estudio", estudio);
            model.addAttribute("imagenes", imagenes);

            return "views/detalle";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


}
