package com.example.grupo.belmg.paginavideojuegos.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    //@JsonIgnore                                                 //<--- EVITA UN ERROR EN EL POSTMAN CON RELACIONES BIDIRECCIONALES MANYTOONE
    //@ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message="Es requerido el estudio")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_estudio", nullable = false)
    private Estudio estudio;


    @ManyToMany(mappedBy = "videojuegos")
    private List<Categoria> categoria;


    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "videojuegos_comentarios_valoraciones",
            joinColumns = @JoinColumn(name = "videojuego_id"),
            inverseJoinColumns = @JoinColumn(name = "comentario_valoracion_id")
    )
    private List<Comentarios_Valoracion> comentarios_valoraciones = new ArrayList<Comentarios_Valoracion>();

}
