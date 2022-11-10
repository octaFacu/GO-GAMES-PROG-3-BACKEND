package com.example.grupo.belmg.paginavideojuegos.Controladores;

import com.example.grupo.belmg.paginavideojuegos.Entidades.Compra;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Usuario;
import com.example.grupo.belmg.paginavideojuegos.Entidades.Videojuego;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioCompra;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioUsuario;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ImplementacionServicioVideojuego;
import com.example.grupo.belmg.paginavideojuegos.Servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
@RequestMapping(path = "compra")
public class ControladorCompra extends ImplementacionControladorBase<Compra, ImplementacionServicioCompra>{

    @Autowired
    ImplementacionServicioUsuario servicioUsuario;

    @Autowired
    ImplementacionServicioVideojuego servicioVideojuego;

    @GetMapping("/searchCompra/{id}")
    public ResponseEntity<?> searchCompra(@PathVariable Long id){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findCompraByUserId(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage()+"\"}"));
        }
    }

    @GetMapping("/detalle-compra")
    public String detalleCompra(Model model){
        try{

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            long id = servicioUsuario.traerIdUsuarioActual(email);
            Usuario usuario = this.servicioUsuario.findById(id);
            model.addAttribute("usuario",usuario);

            long idVideojuego = 1;
            Videojuego videojuego = this.servicioVideojuego.findById(idVideojuego);
            model.addAttribute("videojuego", videojuego);


            return "views/formulario/detalleCompra";
        }catch (Exception e){
            return "/error";
        }
    }

    @PostMapping("/detalle-compra/{id}/{idUsuario}")
    public String compraRealizada(@PathVariable("id")long id, @PathVariable("idUsuario")long idUsuario) {
        try{
            Videojuego videojuego = this.servicioVideojuego.findById(id);
            Compra compra = new Compra();
            compra.setPrecio_total((int) (videojuego.getPrecio()+(videojuego.getPrecio()*0.65)));

            Usuario usuario = this.servicioUsuario.findById(idUsuario);
            System.out.println(usuario.getNombre());

            compra.setUsuario(usuario);
            compra.setVideojuego(videojuego);
            compra.setFecha_de_compra(new Date());


            System.out.println(videojuego.getPrecio());
            service.save(compra);
            return "redirect:/usuarios/inicio";
        }catch (Exception e){
            return "/error";
        }
    }

}
