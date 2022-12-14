package com.example.grupo.belmg.paginavideojuegos.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comentarios_valoracion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comentarios_Valoracion extends Base{



    @Size(min = 1,max = 500,message = "el mensaje debe contener al menos un caracter y menos de 500")
    @NotNull
    private String comentario;

    //@Size(min = 1,max = 5)
    @NotNull
    private int puntaje;


    //ver si relacion esta bien
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_usuario")
    @JsonIgnore
    private Usuario usuario;


}
