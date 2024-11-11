package com.example.transporte.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombres;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Los apellidos no pueden estar vacíos")
    private String apellidos;

    @Column(nullable = false, length = 8, unique = true)
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    private String dni;

    @Column(nullable = false)
    @Min(value = 18, message = "La edad mínima es 18 años")
    private int edad;

    @Column(nullable = false, length = 15)
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipoUsuario;



    @NotEmpty(message = "La contraseña no puede estar vacía")
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(nullable = false)
    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String contraseña;

    public enum TipoUsuario {
        PASAJERO,
        ADMINISTRADOR
    }
}
