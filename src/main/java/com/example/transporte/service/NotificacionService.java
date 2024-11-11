package com.example.transporte.service;

import com.example.transporte.model.*;
import com.example.transporte.repository.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class NotificacionService {

    private final RutaRepository rutaRepository;
    private final PasajeRepository pasajeRepository;
    private final HistorialNotificacionRepository historialNotificacionRepository;

    public NotificacionService(RutaRepository rutaRepository,
                               PasajeRepository pasajeRepository,
                               HistorialNotificacionRepository historialNotificacionRepository) {
        this.rutaRepository = rutaRepository;
        this.pasajeRepository = pasajeRepository;
        this.historialNotificacionRepository = historialNotificacionRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void enviarNotificacionesViaje() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaMañana = fechaActual.plusDays(1);

        List<Ruta> rutas = rutaRepository.findByFechaViaje(fechaMañana);

        for (Ruta ruta : rutas) {
            List<Pasaje> pasajes = pasajeRepository.findByRutaIdRuta(ruta.getIdRuta());

            for (Pasaje pasaje : pasajes) {
                HistorialNotificacion notificacion = new HistorialNotificacion();
                notificacion.setUsuario(pasaje.getUsuario());
                notificacion.setMensaje("Recordatorio: Su viaje en la ruta " + ruta.getIdRuta() + " es mañana.");
                historialNotificacionRepository.save(notificacion);
            }
        }
    }
}
