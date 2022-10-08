package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categorias")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Se necesita el nombre de la categoria.")
    @Size(min=3,max=25, message="La categoria debe tener entre 5 y 25 caracteres.")
    private String tipo;

    //private List<Videojuego> videojuegos;
}
