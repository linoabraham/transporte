package com.example.transporte.repository;

import com.example.transporte.model.Pasaje;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PasajeRepository extends JpaRepository<Pasaje, Long> {
    List<Pasaje> findByRutaIdRuta(Long idRuta);
    List<Pasaje> findByUsuarioIdUsuario(Long idUsuario);
    Optional<Pasaje> findByUsuarioIdUsuarioAndRutaIdRuta(Long idUsuario, Long idRuta);
    Optional<Pasaje> findByRutaIdRutaAndNumeroAsiento(Long idRuta, int numeroAsiento);
}
