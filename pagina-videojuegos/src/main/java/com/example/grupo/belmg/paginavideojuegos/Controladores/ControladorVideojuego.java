package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioCategoria;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioEstudio;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
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

    //CRUD
    @GetMapping("/crudVideojuego")
    public String crudVideojuego(Model model){

        try{
            List<Videojuego> videojuegos = this.servicioVideojuego.findAll();
            model.addAttribute("videojuegos", videojuegos);
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


    @GetMapping("/activar/videojuego/{id}")
    public String ponerVideojuego(Model model, @PathVariable("id") long id){

        try{

            model.addAttribute("videojuego", this.servicioVideojuego.findById(id));
            return "views/formulario/eliminar";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/activar/videojuego/{id}")
    public String activarVideojuego(Model model, @PathVariable("id") long id){

        try{
            this.servicioVideojuego.deleteActivoById(id);
            return "redirect:/videojuego/crudVideojuego";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    //FIN CRUD




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

}
