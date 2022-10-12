package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Entity
@Table(name = "videojuegos")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Videojuego extends Producto {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha no puede ser nula")
    @PastOrPresent(message = "debe ser igual o menor a la fecha de hoy")
    private Date fecha_lanzamiento;

    private boolean PG;

    @NotNull(message="Es requerido el estudio")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_estudio", nullable = false)
    private Estudio estudio;

    /*@NotNull(message="Es requerido la categoria")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_categoria", nullable = false)
    private Categoria categoria;*/

}
