package com.example.grupo.belmg.paginavideojuegos.Entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario extends Base{

    @NotEmpty(message = "Se requiere su nombre")
    private String nombre;


    @NotEmpty(message = "Se requiere su apellido")
    private String apellido;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message="No puede ser nulo la fecha")
    @PastOrPresent(message="La fecha no puede ser mayor a la actual")
    private Date cumplanios;

    @NotEmpty(message = "Se requiere su nombre de usuario")
    private String nombre_usuario;

    @NotEmpty(message = "Se requiere su contraseña")

    private String contrasenia;

    @NotEmpty(message = "Se requiere su email")
    @Email
    private String email;

    private boolean admin;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
    )
    private Collection<Rol> rol;


    //o remove no lo se y ver relacion 1 - 1 o 1-*
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_direccion")
    private Direccion direccion;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_tarjeta")
    private DetallesTarjeta tarjeta;

    //RELACION CON COMPRA


    @OneToMany(mappedBy = "usuario")
    //@JsonIgnore
    //@JsonManagedReference
    private List<Compra> compras;



}
