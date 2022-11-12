package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@MappedSuperclass
//@Table(name = "productos")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Producto extends Base{


    @Min(value = 5, message = "El valor tiene que ser superior a 5")
    @Max(value = 30000, message = "El valor tiene que ser inferior a 30000")
    private int precio;

    @NotNull(message = "Se necesita ingresar un nombre")
    @Size(min=1,max=40, message="El nombre del producto debe tener entre 1 y 40 caracteres.")
    private String nombre;

    @Size(min=5,max=255, message="El nombre debe tener entre 5 y 255 caracteres.")
    private String descripcion;

    private boolean activo = true;

    @NotEmpty(message = "Se necesita ingresar un link de una imagen")
    private String img_portada;



}



