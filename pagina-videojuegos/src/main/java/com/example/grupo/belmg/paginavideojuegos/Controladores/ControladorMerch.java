package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.*;
import com.example.grupo.belmg.paginavideojuegos.Servicios.*;
import com.example.grupo.belmg.paginavideojuegos.Utilidades.ValidadorURL;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@RestController
//@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/merch")
public class ControladorMerch extends ImplementacionControladorBase<Merch, ImplementacionServicioMerch>{


    private ValidadorURL validadorURL = new ValidadorURL();
    @Autowired
    private ImplementacionServicioMerch servicioMerch;

    @Autowired
    private ImplementacionServicioFabricante servicioFabricante;

    @Autowired
    private ImplementacionServicioImagen servicioImagen;


    //-------------- CRUD MERCH -------------------


    @GetMapping("/crudMerch")
    public String findAll(@RequestParam Map<String, Object> params, Model model){

        try{

            int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

            PageRequest pageRequest = PageRequest.of(page,5);

            Page<Merch> pageMerch = servicioMerch.getAll(pageRequest);

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

            return "views/crudMerch";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/formulario/merch/{id}")
    public String formularioVideojuego(Model model, @PathVariable("id") long id){

        try{
            model.addAttribute("fabricantes", this.servicioFabricante.findAll());

            if(id == 0){
                model.addAttribute("merch", new Merch());
            }else{
                model.addAttribute("merch", this.servicioMerch.findById(id));
            }

            return "views/formulario/merch";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/formulario/merch/{id}")
    public String guardarVideojuego(@Valid @ModelAttribute("merch") Merch merch, BindingResult result, Model model, @PathVariable("id") long id){

        try{

            boolean URLvalid;
            model.addAttribute("fabricantes", this.servicioFabricante.findAll());

            if(result.hasErrors()){
                return "views/formulario/merch";
            }

            //valida links

            if(validadorURL.validarURL(merch.getImg_portada()) == false){
                //System.out.println("false");
                URLvalid = false;
                model.addAttribute("URLvalid", URLvalid);
                return "views/formulario/merch";
            }else{
                URLvalid = true;
                model.addAttribute("URLvalid", URLvalid);
            }

            if(id == 0){
                this.servicioMerch.save(merch);
            }else{
                this.servicioMerch.update(id, merch);
            }

            return "redirect:/merch/crudMerch";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/eliminar/merch/{id}")
    public String eliminarVideojuego(Model model, @PathVariable("id") long id){

        try{

            model.addAttribute("merch", this.servicioMerch.findById(id));
            return "views/formulario/eliminarmerch";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/eliminar/merch/{id}")
    public String desactivarVideojuego(Model model, @PathVariable("id") long id){

        try{
            this.servicioMerch.deleteActivoById(id);
            return "redirect:/merch/crudMerch";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    //-------------- IMAGANES CRUD MERCH -------------------

    @GetMapping("/ingresoimg/merch/{id}")
    public String ingresoimgVideojuego(Model model, @PathVariable("id") long id){

        try{

            int cantImagenes = this.servicioImagen.findImagenByMerchId(id).size();

            model.addAttribute("merch", this.servicioMerch.findById(id));
            model.addAttribute("imagenes", this.servicioImagen.findImagenByMerchId(id));
            model.addAttribute("imagen", new Imagen());
            model.addAttribute("cantImagenes", cantImagenes);

            return "views/formulario/ingresoimgmerch";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/postingresoimg/merch/{id}")
    public String finingresoimgVideojuego(@Valid @ModelAttribute("imagen") Imagen imagen,BindingResult result, Model model, @PathVariable("id") long id) {

        try {
            boolean URLvalid;
            int cantImagenes = this.servicioImagen.findImagenByMerchId(id).size();

            model.addAttribute("cantImagenes", cantImagenes);
            model.addAttribute("merch", this.servicioMerch.findById(id));
            model.addAttribute("imagenes", this.servicioImagen.findImagenByMerchId(id));

            if(result.hasErrors()){
                return "views/formulario/ingresoimgmerch";
            }

            if(validadorURL.validarURL(imagen.getLink()) == false){

                URLvalid = false;
                model.addAttribute("URLvalid", URLvalid);
                return "views/formulario/ingresoimgmerch";
            }else{
                URLvalid = true;
                model.addAttribute("URLvalid", URLvalid);
            }

            imagen.setMerch(this.servicioMerch.findById(id));
            servicioImagen.save(imagen);

            return "redirect:/merch/ingresoimg/merch/{id}";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    @PostMapping("/inicioeliminarimg/merch/{idMerch}")
    public String inicioEliminaimgVideojuego(@ModelAttribute("imagen") Imagen imagen, @PathVariable("idMerch") long idMerch, Model model){

        try{

            long imagenid = imagen.getId();

            Imagen img = this.servicioImagen.findById(imagenid);
            String imagenlink = img.getLink();
            //System.out.println("ID IMAGEN: " + imagenid);
            //System.out.println("LINK IMAGEN: " + imagenlink);

            model.addAttribute("merch", this.servicioMerch.findById(idMerch));
            model.addAttribute("imagenes", this.servicioImagen.findImagenByVideojuegoId(idMerch));
            model.addAttribute("imagenid", imagenid);
            model.addAttribute("imagenlink", imagenlink);

            return "views/formulario/eliminarimgmerch";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/posteliminarimg/merch/{idMerch}/{id}")
    public String postEliminaimgVideojuego(@PathVariable("idMerch") long idMerch,@PathVariable("id") long id, Model model){

        try{

            this.servicioImagen.delete(id);

            return "redirect:/merch/ingresoimg/merch/{idMerch}";

        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    //-------------- FIN IMAGANES CRUD MERCH -------------------


    //-------------- FIN CRUD MERCH -------------------


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

    @PostMapping("/deleteOneActivo/{id}")
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
