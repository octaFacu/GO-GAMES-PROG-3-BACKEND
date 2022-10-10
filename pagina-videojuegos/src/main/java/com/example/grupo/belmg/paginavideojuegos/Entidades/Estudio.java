package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "estudios")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Estudio extends Base{



    @NotEmpty(message = "Se necesita el nombre del estudio.")
    @Size(min=3,max=30, message="El nombre del estudio debe tener entre 5 y 30 caracteres.")
    private String nombre;

    //private List<Videojuego> videojuegos;
}
