package com.example.transporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name = "rutas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRuta;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "El origen no puede estar vacío")
    private String origen;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "El destino no puede estar vacío")
    private String destino;

    @Column(nullable = false)
    @NotNull(message = "La fecha de viaje no puede ser nula")
    private LocalDate fechaViaje;

    @Column(nullable = false)
    @NotNull(message = "La hora de salida no puede ser nula")
    private LocalTime horaSalida;

    @ManyToOne
    @JoinColumn(name = "idVehiculo", nullable = false)
    @NotNull(message = "El vehículo no puede ser nulo")
    private Vehiculo vehiculo;

    @Column(nullable = false)
    private int asientosDisponibles;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser positivo")
    private double precio;

    // Este método se ejecutará solo al crear la ruta por primera vez
    @PrePersist
    private void establecerAsientosDisponibles() {
        this.asientosDisponibles = 20;
    }

}