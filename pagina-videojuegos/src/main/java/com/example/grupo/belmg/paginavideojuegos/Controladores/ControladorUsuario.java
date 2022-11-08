package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.DetallesTarjeta;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Direccion;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioDetallesTarjeta;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioDireccion;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("usuarios")
public class ControladorUsuario extends ImplementacionControladorBase<Usuario, ImplementacionServicioUsuario>{

    @Autowired
    ImplementacionServicioDireccion servicioDireccion;

    @Autowired
    ImplementacionServicioDetallesTarjeta servicioTarjeta;
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


    @GetMapping("/direccion-tarjeta")
    public String direccionTarjeta(Model modelo){
        try {


            modelo.addAttribute("direccion",new Direccion());
            modelo.addAttribute("tarjeta",new DetallesTarjeta());
            return "views/formulario/direccion-tarjeta";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("/direccion-tarjeta")
    public String FormulariodireccionTarjeta(Model modelo, @ModelAttribute ("direccion") Direccion direccion,
                                             @Valid @ModelAttribute ("tarjeta") DetallesTarjeta tarjeta,BindingResult result){
        try {
            if (result.hasErrors()){
                return "views/formulario/direccion-tarjeta";
            }
            //esto nos sirve para traer el mail del usuario autenticado en el momento para poder hacer una query y saber la id en la base de datos
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            long id = this.service.traerIdUsuarioActual(email);
            System.out.println(id);

            servicioDireccion.save(direccion);
            servicioTarjeta.save(tarjeta);

            service.guardarDireccionYTarjeta(id,tarjeta.getId(),direccion.getId());
            return "views/formulario/direccion-tarjeta";
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
