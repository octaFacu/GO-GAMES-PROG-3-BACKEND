package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "detalles_tarjeta")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetallesTarjeta extends Base{


    @NotNull(message = "Se necesita el nombre del titular.")
    @Size(min=3,max=25, message="El nombre debe tener entre 5 y 25 caracteres.")
    private String nombre_titular;

    @NotNull(message = "Se necesita el apellido del titular.")
    @Size(min=3,max=30, message="El apellido debe tener entre 5 y 30 caracteres.")
    private String apellido_titular;

    @NotNull(message = "Se necesita el numero de la tarjeta.")
    //@Size(min=12,max=19, message="Debe tener entre 5 y 30 numeros.")
    private long nro_tarjeta;

    @NotNull(message = "Se necesita el cvv de la tarjeta.")
    //@Size(min=3,max=3, message="Debe tener 3 digitos.")
    private int cvv;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "Se necesita la fecha de vencimiento de la tarjeta.")
    private Date fecha_vencimiento;

}
