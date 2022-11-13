package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.*;
import com.example.grupo.belmg.paginavideojuegos.Servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("usuarios")
public class ControladorUsuario extends ImplementacionControladorBase<Usuario, ImplementacionServicioUsuario>{
    static int i = 0;
    static String guardaAnterior = null;
    @Autowired
    ImplementacionServicioDireccion servicioDireccion;

    @Autowired
    ImplementacionServicioDetallesTarjeta servicioTarjeta;

    @Autowired
    ImplementacionServicioCompra servicioCompra;

    @Autowired
    ImplementacionServicioVideojuego servicioVideojuego;

    @Autowired
    ImplementacionServicioMerch servicioMerch;
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String filtro){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.find(filtro));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage()+"\"}"));
        }
    }

    @GetMapping("inicio")
    public  String inicio(){
        return "views/inicio";
    }

    @GetMapping("registro")
    public String mostrarFormularioRegistro(Model modelo){
        try {
            modelo.addAttribute("usuario", new Usuario());
            return "views/formulario/registro";
        }catch (Exception e){
            return "error";
        }

    }

    @PostMapping("registro")
    public String registro(@Valid @ModelAttribute ("usuario") Usuario usuario,
                           BindingResult result) throws Exception {
        try {
            if(result.hasErrors()){
                return "views/formulario/registro";
            }
            service.save(usuario);
            return "views/formulario/login";
        }catch (Exception e){
            return "error";
        }
    }

    @GetMapping("login")
    public String iniciarSesion(Model modelo){
        try {
            modelo.addAttribute("usuario", new Usuario());
            return  "views/formulario/login";
        }catch (Exception e){
            return "error/404";
        }
    }

    @GetMapping("/")
    public String verInicio(Model modelo){
        try {
            return  "views/inicio";
        }catch (Exception e){
            return "error/404";
        }
    }

    @GetMapping("rgtAdmins")
    public String mostrarFormularioRgtAdmins(Model modelo){
        try {
            modelo.addAttribute("admin", new Usuario());
            return  "views/admin/rgtAdmins";
        }catch (Exception e){
            return "error/404";
        }
    }

    @PostMapping("rgtAdmins")
    public String registroadin(@Valid @ModelAttribute ("admin") Usuario admin, BindingResult result) throws Exception {
        try {
            if (result.hasErrors()){
                return "views/admin/rgtAdmins";
            }
            service.saveAdmin(admin);
            return "redirect:/usuarios/eliminarAdmin";
        }catch (Exception e){
            return "error";
        }
    }

    @GetMapping("/eliminarAdmin")
    public String EliminarAdmins(Model modelo){
        try {
            List<Usuario> admin;
            admin = this.service.buscarAdmin();
            modelo.addAttribute("admin", admin);
            return  "views/admin/eliminarAdmin";
        }catch (Exception e){
            return "error/404";
        }
    }
    @PostMapping("/eliminarAdmin/{id}")
    public String EliminarAdmin(Model modelo,@PathVariable("id")long id){
        try {
            Usuario usuario = this.service.findById(id);
            modelo.addAttribute("admin", usuario);
            this.service.delete(id);
            return "redirect:/usuarios/eliminarAdmin";
        }catch (Exception e){
            return "error/404";
        }
    }


    @GetMapping("/direccion-tarjeta/{id}")
    public String direccionTarjeta(Model modelo,HttpServletRequest request,@PathVariable("id")long id){
        try {

            Usuario usuario = this.service.findById(this.service.obtenerUsuario());
            if(id == 0){
                modelo.addAttribute("direccion",new Direccion());
                modelo.addAttribute("tarjeta",new DetallesTarjeta());
            }else {
                modelo.addAttribute("direccion", this.servicioDireccion.findById(id));
                modelo.addAttribute("tarjeta", this.servicioTarjeta.findById(id));
            }

            String http = request.getHeader("Referer");
            modelo.addAttribute("httpRedireccion", http);

            return "views/formulario/direccion-tarjeta";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("/direccion-tarjeta/{id}")
    public String FormulariodireccionTarjeta(Model modelo, @Valid @ModelAttribute ("direccion") Direccion direccion,BindingResult result2,
                                             @Valid @ModelAttribute ("tarjeta") DetallesTarjeta tarjeta,
                                             BindingResult result, @RequestParam("prueba")String referer,@PathVariable("id") long id){
        try {
            if (result.hasErrors()){
                if(i==0){
                    guardaAnterior = referer;
                    i++;
                }
                System.out.println(guardaAnterior);
                return "views/formulario/direccion-tarjeta";
            }
            if (result2.hasErrors()){
                if(i==0){
                    guardaAnterior = referer;
                    i++;
                }

                return "views/formulario/direccion-tarjeta";
            }

            Usuario usuario = this.service.findById(this.service.obtenerUsuario());
            if(id == 0){
                servicioTarjeta.save(tarjeta);
                servicioDireccion.save(direccion);
                service.guardarDireccionYTarjeta(this.service.obtenerUsuario(),tarjeta.getId(),direccion.getId());


            }else {
                servicioTarjeta.update(usuario.getTarjeta().getId(), tarjeta);
                servicioDireccion.update(usuario.getDireccion().getId(),direccion);
            }

            if(i==0){
                return "redirect:"+ referer;
            }else{
                return "redirect:"+ guardaAnterior;
            }

        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/videojuego/biblioteca")
    public String biblioteca(Model model){
        try {


            List<Compra> compras = this.servicioCompra.idvideojuegosComprados(this.service.obtenerUsuario());

            List<Videojuego> videojuegos = new ArrayList<>();

            model.addAttribute("compra",compras);

            for (int i=0; i<compras.size();i++){
                if(compras.get(i).getVideojuego() != null){
                    videojuegos.add(compras.get(i).getVideojuego());
                }
            }
            model.addAttribute("videojuegos",videojuegos);

            return "views/biblioteca";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/merch/biblioteca")
    public String bibliotecaMerch(Model model){
        try {
            List<Compra> compras = this.servicioCompra.idvideojuegosComprados(this.service.obtenerUsuario());

            List<Merch> merch = new ArrayList<>();

            model.addAttribute("compra",compras);

            for (int i=0; i<compras.size();i++){
                if(compras.get(i).getMerch() != null){
                    merch.add(compras.get(i).getMerch());
                }
            }
            model.addAttribute("merch",merch);

            return "views/bibliotecaMerch";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
