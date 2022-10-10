package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "productos")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 5, message = "El valor tiene que ser superior a 5")
    @Max(value = 30000, message = "El valor tiene que ser inferior a 30000")
    private int precio;

    @NotEmpty(message = "Se necesita ingresar un nombre")
    @Size(min=3,max=40, message="El nombre del producto debe tener entre 5 y 40 caracteres.")
    private String nombre;

    @Size(min=3,max=25, message="El nombre debe tener entre 5 y 25 caracteres.")
    private String descripcion;

    private boolean activo = true;

    private String img_portada;

    private String[] imagenes;

}
