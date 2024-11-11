package com.example.transporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialNotificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @NotNull(message = "El usuario no puede ser nulo")
    private Usuario usuario;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotEmpty(message = "El mensaje no puede estar vacío")
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechaEnvio = LocalDateTime.now();
}
