package com.example.transporte.controller;

import com.example.transporte.model.Ruta;
import com.example.transporte.service.RutaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutas")
public class RutaController {

    private final RutaService rutaService;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @PostMapping
    public Ruta crearRuta(@Valid @RequestBody Ruta ruta) {
        ruta.setAsientosDisponibles(20); // Siempre 20 asientos al crear una ruta
        return rutaService.crearRuta(ruta);
    }

    @GetMapping
    public List<Ruta> obtenerRutas() {
        return rutaService.obtenerRutas();
    }

    @GetMapping("/{id}")
    public Ruta obtenerRutaPorId(@PathVariable Long id) throws Exception {
        return rutaService.obtenerRutaPorId(id);
    }

    @PutMapping("/{id}")
    public Ruta actualizarRuta(@PathVariable Long id, @Valid @RequestBody Ruta rutaActualizada) throws Exception {
        rutaActualizada.setAsientosDisponibles(20); // Siempre 20 asientos al actualizar una ruta
        return rutaService.actualizarRuta(id, rutaActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminarRuta(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
    }

    @GetMapping("/buscar")
    public List<Ruta> buscarPorOrigenYDestino(@RequestParam String origen, @RequestParam String destino) {
        return rutaService.buscarPorOrigenYDestino(origen, destino);
    }
}
