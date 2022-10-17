package com.example.grupo.belmg.paginavideojuegos.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria extends Base{



    @NotEmpty(message = "Se necesita el nombre de la categoria.")
    @Size(min=3,max=25, message="La categoria debe tener entre 5 y 25 caracteres.")
    private String tipo;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "categorias_videojuegos",
            joinColumns = { @JoinColumn(name = "categorias_id") },
            inverseJoinColumns = { @JoinColumn(name = "videojuegos_id") })
    private List<Videojuego> videojuegos = new ArrayList<Videojuego>();
}
