package com.example.grupo.belmg.paginavideojuegos.Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @Min(value = 10000000000000L, message = "ingrese una tarjeta valida")
    @Max(value = 100000000000000000L, message = "ingrese una tarjeta valida")
    private long nro_tarjeta;

    @NotNull(message = "Se necesita el cvv de la tarjeta.")
    @Min(value = 100, message = "como minimo el cvv tiene 3 valores")
    @Max(value = 1000, message = "como maximo el cvv tiene 3 digitos")
    private int cvv;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Se necesita la fecha de vencimiento de la tarjeta.")
    private Date fecha_vencimiento;

}
