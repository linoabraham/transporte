package com.example.transporte.service;

import com.example.transporte.model.Ruta;
import com.example.transporte.repository.RutaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RutaService {

    private final RutaRepository rutaRepository;

    public RutaService(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    public Ruta crearRuta(Ruta ruta) {
        ruta.setAsientosDisponibles(20); // Siempre 20 asientos al crear una ruta
        return rutaRepository.save(ruta);
    }

    public List<Ruta> obtenerRutas() {
        return rutaRepository.findAll();
    }

    public Ruta obtenerRutaPorId(Long id) throws Exception {
        return rutaRepository.findById(id)
                .orElseThrow(() -> new Exception("Ruta no encontrada"));
    }

    public Ruta actualizarRuta(Long id, Ruta rutaActualizada) throws Exception {
        Ruta ruta = obtenerRutaPorId(id);
        ruta.setOrigen(rutaActualizada.getOrigen());
        ruta.setDestino(rutaActualizada.getDestino());
        ruta.setFechaViaje(rutaActualizada.getFechaViaje());
        ruta.setHoraSalida(rutaActualizada.getHoraSalida());
        ruta.setVehiculo(rutaActualizada.getVehiculo());
        ruta.setPrecio(rutaActualizada.getPrecio());
        // No modificamos asientosDisponibles aquí
        return rutaRepository.save(ruta);
    }

    public void eliminarRuta(Long id) {
        rutaRepository.deleteById(id);
    }

    public List<Ruta> buscarPorOrigenYDestino(String origen, String destino) {
        return rutaRepository.findByOrigenAndDestino(origen, destino);
    }
}
