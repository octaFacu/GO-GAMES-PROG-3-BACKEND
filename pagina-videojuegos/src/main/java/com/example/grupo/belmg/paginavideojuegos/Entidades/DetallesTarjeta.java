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


    @NotEmpty(message = "Se necesita el nombre del titular.")
    private String nombre_titular;

    @NotEmpty(message = "Se necesita el apellido del titular.")
    private String apellido_titular;

    @NotNull(message = "Se necesita el numero de la tarjeta.")
    private long nro_tarjeta;

    @NotNull(message = "Se necesita el cvv de la tarjeta.")
    //@Size(min=3,max=3, message="Debe tener 3 digitos.")
    private int cvv;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Se necesita la fecha de vencimiento de la tarjeta.")
    private Date fecha_vencimiento;

}
