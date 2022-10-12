package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "compras")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Compra extends Base{



    @NotEmpty(message = "Introduzca la fecha de la facturacion.")
    private Date fecha_de_compra;

    @NotEmpty(message = "Introduzca el precio total de la compra.")
    private int precio_total;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "videojuego")
    private Videojuego videojuego;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merch")
    private Merch merch;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
}
