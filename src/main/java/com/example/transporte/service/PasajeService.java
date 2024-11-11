package com.example.transporte.service;

import com.example.transporte.model.*;
import com.example.transporte.repository.*;
import com.example.transporte.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PasajeService {
        private final PasajeRepository pasajeRepository;
        private final RutaRepository rutaRepository;
        private final UsuarioRepository usuarioRepository;
        private final HistorialNotificacionRepository historialNotificacionRepository;

        public PasajeService(PasajeRepository pasajeRepository,
                             RutaRepository rutaRepository,
                             UsuarioRepository usuarioRepository,
                             HistorialNotificacionRepository historialNotificacionRepository) {
            this.pasajeRepository = pasajeRepository;
            this.rutaRepository = rutaRepository;
            this.usuarioRepository = usuarioRepository;
            this.historialNotificacionRepository = historialNotificacionRepository;
        }

        @Transactional
        public Pasaje comprarPasaje(Long idUsuario, Long idRuta, int numeroAsiento) {
            // Validar existencia del usuario
            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

            // Validar existencia de la ruta
            Ruta ruta = rutaRepository.findById(idRuta)
                    .orElseThrow(() -> new ResourceNotFoundException("Ruta no encontrada"));

            // Validar número de asiento entre 1 y 20
            if (numeroAsiento < 1 || numeroAsiento > 20) {
                throw new BadRequestException("El número de asiento debe estar entre 1 y 20");
            }

            // Validar si el usuario ya tiene un pasaje en la misma ruta
            Optional<Pasaje> pasajeExistente = pasajeRepository.findByUsuarioIdUsuarioAndRutaIdRuta(idUsuario, idRuta);
            if (pasajeExistente.isPresent()) {
                throw new BadRequestException("El usuario ya tiene un pasaje para esta ruta");
            }

            // Validar si el asiento está disponible
            Optional<Pasaje> asientoOcupado = pasajeRepository.findByRutaIdRutaAndNumeroAsiento(idRuta, numeroAsiento);
            if (asientoOcupado.isPresent()) {
                throw new BadRequestException("El asiento " + numeroAsiento + " ya está ocupado en esta ruta");
            }

            // Validar si hay asientos disponibles
            if (ruta.getAsientosDisponibles() <= 0) {
                throw new BadRequestException("No hay asientos disponibles en esta ruta");
            }

            // Registrar el pasaje
            Pasaje pasaje = new Pasaje();
            pasaje.setUsuario(usuario);
            pasaje.setRuta(ruta);
            pasaje.setNumeroAsiento(numeroAsiento);
            pasajeRepository.save(pasaje);

            // Actualizar asientos disponibles en la ruta
            ruta.setAsientosDisponibles(ruta.getAsientosDisponibles() - 1);
            rutaRepository.save(ruta); // Guardar la ruta actualizada

            // Registrar notificación
            HistorialNotificacion notificacion = new HistorialNotificacion();
            notificacion.setUsuario(usuario);
            notificacion.setMensaje("Compra exitosa: Asiento " + numeroAsiento + " en la ruta " + idRuta);
            historialNotificacionRepository.save(notificacion);

            return pasaje;
        }

    public List<Pasaje> obtenerPasajes() {
        return pasajeRepository.findAll();
    }

    public List<Pasaje> obtenerPasajesPorUsuario(Long idUsuario) {
        return pasajeRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public void cancelarPasaje(Long idPasaje) throws Exception {
        Pasaje pasaje = pasajeRepository.findById(idPasaje)
                .orElseThrow(() -> new Exception("Pasaje no encontrado"));
        Ruta ruta = pasaje.getRuta();
        if (ruta.getAsientosDisponibles() < 20) {
            ruta.setAsientosDisponibles(ruta.getAsientosDisponibles() + 1);
            rutaRepository.save(ruta);
        }
        pasajeRepository.deleteById(idPasaje);
    }
}
