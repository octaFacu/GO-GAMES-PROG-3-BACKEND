package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "imagenes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Imagen extends Base{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_videojuego")
    private Videojuego videojuego;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_merch")
    private Merch merch;

    @NotEmpty(message = "Se necesita que ingrese un link de una imagen")
    private String link;
}
