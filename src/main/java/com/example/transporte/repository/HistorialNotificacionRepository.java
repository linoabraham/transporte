package com.example.transporte.repository;

import com.example.transporte.model.HistorialNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistorialNotificacionRepository extends JpaRepository<HistorialNotificacion, Long> {
    List<HistorialNotificacion> findByUsuarioIdUsuario(Long idUsuario);
}
