package com.example.transporte.controller;

import com.example.transporte.model.HistorialNotificacion;
import com.example.transporte.repository.HistorialNotificacionRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final HistorialNotificacionRepository historialNotificacionRepository;

    public NotificacionController(HistorialNotificacionRepository historialNotificacionRepository) {
        this.historialNotificacionRepository = historialNotificacionRepository;
    }

    @GetMapping
    public List<HistorialNotificacion> obtenerTodasLasNotificaciones() {
        return historialNotificacionRepository.findAll();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<HistorialNotificacion> obtenerNotificacionesPorUsuario(@PathVariable Long idUsuario) {
        return historialNotificacionRepository.findByUsuarioIdUsuario(idUsuario);
    }
}
