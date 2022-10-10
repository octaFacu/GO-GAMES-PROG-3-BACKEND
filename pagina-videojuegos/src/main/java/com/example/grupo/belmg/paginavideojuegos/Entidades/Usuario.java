package com.example.grupo.belmg.paginavideojuegos.Entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotEmpty(message = "Se requiere su nombre")
    private String nombre;


    @NotEmpty(message = "Se requiere su apellido")
    private String apellido;

    @Past
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotEmpty(message = "Se requiere su fecha de cumpleaños")
    private Date Cumpleaños;

    @NotEmpty(message = "Se requiere su nombre de usuario")
    private String nombre_usuario;

    @NotEmpty(message = "Se requiere su contraseña")
    private String Contraseña;

    @NotEmpty(message = "Se requiere su email")
    @Email
    private String email;

    private boolean admin;


    //creo que persist para no borrar las compras si borro el usuariox
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Compra> compra;

    //o remove no lo se y ver relacion 1 - 1 o 1-*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_direccion")
    private Direccion direccion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tarjeta")
    private DetallesTarjeta tarjeta;


}
