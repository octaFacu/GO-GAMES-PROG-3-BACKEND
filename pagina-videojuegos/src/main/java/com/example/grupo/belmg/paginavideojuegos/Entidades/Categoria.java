package com.example.grupo.belmg.paginavideojuegos.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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



    @NotNull(message = "Se necesita el nombre de la categoria.")
    @Size(min=3,max=25, message="La categoria debe tener entre 5 y 25 caracteres.")
    private String tipo;

    @JsonBackReference
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
