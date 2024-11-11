package com.example.transporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pasajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pasaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPasaje;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @NotNull(message = "El usuario no puede ser nulo")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idRuta", nullable = false)
    @NotNull(message = "La ruta no puede ser nula")
    private Ruta ruta;

    @Column(nullable = false)
    @Min(value = 1, message = "El número de asiento mínimo es 1")
    @Max(value = 20, message = "El número de asiento máximo es 20")
    private int numeroAsiento;

    @Column(nullable = false)
    private LocalDateTime fechaCompra = LocalDateTime.now();
}
