package com.example.grupo.belmg.paginavideojuegos.Entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario extends Base{

    @NotNull(message = "Se requiere su nombre")
    private String nombre;


    @NotNull(message = "Se requiere su apellido")
    private String apellido;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "Se requiere su fecha de cumpleaños")
    @PastOrPresent(message = "debe ser igual o menor a la fecha de hoy")
    private Date cumplanios;

    @NotNull(message = "Se requiere su nombre de usuario")
    private String nombre_usuario;

    @NotNull(message = "Se requiere su contraseña")
    
    private String contrasenia;

    @NotNull(message = "Se requiere su email")
    @Email
    private String email;

    private boolean admin;




    //o remove no lo se y ver relacion 1 - 1 o 1-*
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_direccion")
    private Direccion direccion;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_tarjeta")
    private DetallesTarjeta tarjeta;

    //RELACION CON COMPRA

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;



}
