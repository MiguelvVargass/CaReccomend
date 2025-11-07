package JMC.car.service.impl;

import JMC.car.Entity.Vehiculo;
import JMC.car.repository.VehiculoRepository;
import JMC.car.service.VehiculoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private Vehiculo[] vehiculosArray;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
        // Cargar todos los vehículos desde la base de datos en el array
        List<Vehiculo> list = this.vehiculoRepository.findAll();
        this.vehiculosArray = list.toArray(new Vehiculo[0]);
    }

    @Override
    public List<Vehiculo> listAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    /**
     * Devuelve una copia del array de vehículos cargado desde la base de datos.
     */
    public Vehiculo[] getVehiculosArray() {
        return vehiculosArray != null ? vehiculosArray.clone() : new Vehiculo[0];
    }

    /**
     * Construye un árbol KD desde el array actual de vehículos y busca el vehículo
     * más cercano a los parámetros recibidos.
     *
     * @param esRural         si el vehículo es rural
     * @param esManual        si el vehículo es manual
     * @param cantidadPasajeros número de pasajeros
     * @param ruido           nivel de ruido (double)
     * @param precio          precio (double)
     * @param propulsion      tipo de propulsión (como entero)
     * @return el Vehiculo más cercano según la métrica del árbol KD, o null si no hay vehículos
     */
    public Vehiculo buscarMasCercanoPorParametros(boolean esRural,
                                                  boolean esManual,
                                                  int cantidadPasajeros,
                                                  double ruido,
                                                  double precio,
                                                  int propulsion) {
        if (vehiculosArray == null || vehiculosArray.length == 0) {
            List<Vehiculo> list = this.vehiculoRepository.findAll();
            this.vehiculosArray = list.toArray(new Vehiculo[0]);
            if (vehiculosArray.length == 0) return null;
        }

        ArbolKDService arbol = new ArbolKDService();
        arbol.construirDesdeArray(vehiculosArray);

        return arbol.buscarMasCercanoPorParametros(esRural, esManual, cantidadPasajeros, ruido, precio, propulsion);
    }
}
