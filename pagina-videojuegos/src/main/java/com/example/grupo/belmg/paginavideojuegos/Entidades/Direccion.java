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


    @NotNull(message = "Se requiere su calle")
    private String calle;

    @NotNull(message = "Se requiere su localidad")
    private String localidad;

    @NotNull(message = "Se requiere su pais")
    private String pais;

    private int piso;

    private int num_casa;
}
