package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "direccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion extends Base{


    @NotEmpty(message = "{NotEmpty.Direccion.calle}")
    private String calle;

    @NotEmpty(message =  "{NotEmpty.Direccion.localidad}")
    private String localidad;

    @NotEmpty(message =  "{NotEmpty.Direccion.pais}")
    private String pais;

    private int piso;

    private int num_casa;
}
