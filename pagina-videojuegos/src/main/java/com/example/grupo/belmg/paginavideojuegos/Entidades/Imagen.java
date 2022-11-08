package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    private String link;
}
