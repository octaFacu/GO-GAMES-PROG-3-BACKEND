package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "merchs")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Merch extends Producto {



    @NotNull(message = "Ingrese un numero para la cantidad de stock")
    private int stock;

    //-----relacion unidireccional uno a uno-----

    @NotNull(message="Es requerido el fabricante")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fabricante", nullable = false)
    private Fabricante fabricante;

}
