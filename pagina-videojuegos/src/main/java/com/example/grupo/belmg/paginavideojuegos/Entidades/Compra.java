package com.example.grupo.belmg.paginavideojuegos.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "compras")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compra extends Base{

    @NotNull(message = "Introduzca la fecha de la facturacion.")
    private Date fecha_de_compra;

    @NotNull(message = "Introduzca el precio total de la compra.")
    private int precio_total;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "videojuego")
    private Videojuego videojuego;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merch")
    private Merch merch;

    //Relacion con usuario


    @ManyToOne()
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;


}
