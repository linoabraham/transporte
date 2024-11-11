package com.example.transporte.repository;

import com.example.transporte.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    List<Vehiculo> findByMarcaContaining(String marca);
}
