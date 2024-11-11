package com.example.transporte.controller;

import com.example.transporte.model.Vehiculo;
import com.example.transporte.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PostMapping
    public Vehiculo crearVehiculo(@Valid @RequestBody Vehiculo vehiculo) {
        return vehiculoService.crearVehiculo(vehiculo);
    }

    @GetMapping
    public List<Vehiculo> obtenerVehiculos() {
        return vehiculoService.obtenerVehiculos();
    }

    @GetMapping("/{id}")
    public Vehiculo obtenerVehiculoPorId(@PathVariable Long id) throws Exception {
        return vehiculoService.obtenerVehiculoPorId(id);
    }

    @PutMapping("/{id}")
    public Vehiculo actualizarVehiculo(@PathVariable Long id, @Valid @RequestBody Vehiculo vehiculo) throws Exception {
        return vehiculoService.actualizarVehiculo(id, vehiculo);
    }

    @DeleteMapping("/{id}")
    public void eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(id);
    }

    @GetMapping("/buscar")
    public List<Vehiculo> buscarPorMarca(@RequestParam String marca) {
        return vehiculoService.buscarPorMarca(marca);
    }
}
