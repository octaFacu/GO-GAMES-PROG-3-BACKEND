package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comentarios_valoracion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comentarios_Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1,max = 500,message = "el mensaje debe contener almenos un carater y menos de 500")
    private String comentario;

    @Size(min = 1,max = 5)
    private int puntaje;



    //ver si relacion esta bien
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
}
