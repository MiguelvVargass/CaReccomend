package JMC.car.service.impl;

import JMC.car.Entity.Vehiculo;
import JMC.car.repository.VehiculoRepository;
import JMC.car.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> listAllVehiculos() {
        return vehiculoRepository.findAll();
    }
}
