package com.example.transporte.service;

import com.example.transporte.model.Vehiculo;
import com.example.transporte.repository.VehiculoRepository;
import com.example.transporte.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> obtenerVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo obtenerVehiculoPorId(Long id) throws Exception {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado"));
    }

    public Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculoActualizado) throws Exception {
        Vehiculo vehiculo = obtenerVehiculoPorId(id);
        vehiculo.setMarca(vehiculoActualizado.getMarca());
        vehiculo.setAnio(vehiculoActualizado.getAnio());
        vehiculo.setPlaca(vehiculoActualizado.getPlaca());
        vehiculo.setCapacidadAsientos(vehiculoActualizado.getCapacidadAsientos());
        vehiculo.setFechaMantenimiento(vehiculoActualizado.getFechaMantenimiento());
        vehiculo.setProximoMantenimiento(vehiculoActualizado.getProximoMantenimiento());
        return vehiculoRepository.save(vehiculo);
    }

    public void eliminarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }

    public List<Vehiculo> buscarPorMarca(String marca) {
        return vehiculoRepository.findByMarcaContaining(marca);
    }
}
