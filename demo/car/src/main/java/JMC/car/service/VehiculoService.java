package JMC.car.service;

import JMC.car.Entity.Vehiculo;

import java.util.List;

public interface VehiculoService {
    List<Vehiculo> listAllVehiculos();

    // Buscar vehículo más cercano según parámetros convertidos a vector
    Vehiculo buscarMasCercanoPorParametros(boolean esRural,
                                           boolean esManual,
                                           int cantidadPasajeros,
                                           double ruido,
                                           double precio,
                                           int propulsion);
}
