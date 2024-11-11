package com.example.transporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "La marca no puede estar vacía")
    private String marca;

    @Column(nullable = false)
    @Min(value = 1900, message = "El año debe ser válido")
    private int anio;

    @Column(nullable = false, length = 20, unique = true)
    @NotEmpty(message = "La placa no puede estar vacía")
    private String placa;

    @Column(nullable = false)
    @Min(value = 1, message = "La capacidad mínima es 1")
    @Max(value = 20, message = "La capacidad máxima es 20")
    private int capacidadAsientos;

    private String foto;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @NotNull(message = "La fecha de mantenimiento no puede ser nula")
    private Date fechaMantenimiento;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @NotNull(message = "La fecha del próximo mantenimiento no puede ser nula")
    private Date proximoMantenimiento;

}
