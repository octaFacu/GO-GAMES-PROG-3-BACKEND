package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ControladorUsuario extends ImplementacionControladorBase<Usuario, ImplementacionServicioUsuario>{

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
            return "redirect:/eliminarAdmin";
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
            return "redirect:/eliminarAdmin";
        }catch (Exception e){
            return "error/404";
        }
    }
    @GetMapping("crud")
    public String prueba(Model modelo){
        try {
            return  "views/formulario/crud";
        }catch (Exception e){
            return "error/404";
        }
    }
}
