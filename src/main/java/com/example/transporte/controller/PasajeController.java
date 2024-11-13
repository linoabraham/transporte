package com.example.transporte.controller;

import com.example.transporte.model.Pasaje;
import com.example.transporte.service.PasajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pasajes")
public class PasajeController {

    private final PasajeService pasajeService;

    public PasajeController(PasajeService pasajeService) {
        this.pasajeService = pasajeService;
    }

    @PostMapping("/comprar")
    public ResponseEntity<?> comprarPasaje(@RequestParam Long idUsuario,
                                           @RequestParam Long idRuta,
                                           @RequestParam int numeroAsiento) {
        try {
            Pasaje pasaje = pasajeService.comprarPasaje(idUsuario, idRuta, numeroAsiento);
            return new ResponseEntity<>(pasaje, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Pasaje> obtenerPasajes() {
        return pasajeService.obtenerPasajes();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Pasaje> obtenerPasajesPorUsuario(@PathVariable Long idUsuario) {
        return pasajeService.obtenerPasajesPorUsuario(idUsuario);
    }

    @DeleteMapping("/{idPasaje}")
    public void cancelarPasaje(@PathVariable Long idPasaje) throws Exception {
        pasajeService.cancelarPasaje(idPasaje);
    }
}
