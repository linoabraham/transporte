package com.example.transporte.repository;

import com.example.transporte.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
    List<Ruta> findByOrigenAndDestino(String origen, String destino);
    List<Ruta> findByFechaViaje(LocalDate fechaViaje);
}
