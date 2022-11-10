package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.*;
import com.example.grupo.belmg.paginavideojuegos.Servicios.*;
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

//@RestController
//@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/merch")
public class ControladorMerch extends ImplementacionControladorBase<Merch, ImplementacionServicioMerch>{


    @Autowired
    private ImplementacionServicioMerch servicioMerch;

    @Autowired
    private ImplementacionServicioFabricante servicioFabricante;

    @Autowired
    private ImplementacionServicioImagen servicioImagen;


    @GetMapping("/search")
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

    //----------

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
            return ResponseEntity.status(HttpStatus.OK).body(service.deleteById(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage()+"\"}"));
        }
    }*/

    //merch/detalleM/id
    @GetMapping("/detalleM/{id}")
    public String detalleVideojuego(Model model, @PathVariable("id") Long id) {
        try {
            Merch merch = this.servicioMerch.findByIdAndActivo(id);
            Fabricante fabricante = merch.getFabricante();

            List<Imagen> imagenes = this.servicioImagen.findImagenByMerchId(id);

            model.addAttribute("merch", merch);
            model.addAttribute("fabricante", fabricante);
            model.addAttribute("imagenes", imagenes);

            return "views/detalleM";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    @GetMapping("/merchLista")
    public String mostrarMerch(Model model, @RequestParam Map<String, Object> params) throws Exception {


        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

        PageRequest pageRequest = PageRequest.of(page,5);

        Page<Merch> pageMerch = servicioMerch.findAll(pageRequest);

        int totalPage = pageMerch.getTotalPages();                                                     //Total de paginas que tienen los datos de la base de datos(cuantos links se muestran)
        if(totalPage > 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());   //Se crea un listado de numeros desde el 1 al numero final
            model.addAttribute("pages", pages);
        }


        model.addAttribute("merchs", pageMerch.getContent());
        model.addAttribute("current", page + 1);                                        //Parametro para identificar la pagina actual
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return "views/merchLista";
    }


    @GetMapping(value = "/busquedaMerch")
    public String busquedaMerch(Model model, @RequestParam(value ="query",required = false)String q, @RequestParam Map<String, Object> params){
        try {

            int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

            PageRequest pageRequest = PageRequest.of(page,5);

            Page<Merch> pageMerch = this.servicioMerch.findByNombre(q, pageRequest);

            int totalPage = pageMerch.getTotalPages();                                                     //Total de paginas que tienen los datos de la base de datos(cuantos links se muestran)
            if(totalPage > 0){
                List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());   //Se crea un listado de numeros desde el 1 al numero final
                model.addAttribute("pages", pages);
            }

            model.addAttribute("merchs", pageMerch.getContent());
            model.addAttribute("current", page + 1);                                        //Parametro para identificar la pagina actual
            model.addAttribute("next", page + 2);
            model.addAttribute("prev", page);
            model.addAttribute("last", totalPage);
            model.addAttribute("resultado",q);

            return "views/busquedaMerch";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
