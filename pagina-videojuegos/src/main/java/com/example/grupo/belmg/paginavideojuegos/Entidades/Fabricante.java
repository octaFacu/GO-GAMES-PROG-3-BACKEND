package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "fabricantes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Fabricante extends Base{



    @NotEmpty(message = "Se necesita el nombre del fabricante.")
    @Size(min=3,max=30, message="El nombre del fabricante debe tener entre 5 y 30 caracteres.")
    private String nombre;

    //-----relacion unidireccional uno a uno-----

    /*@OneToOne(mappedBy = "fabricante")
    private Merch merch;*/

}
