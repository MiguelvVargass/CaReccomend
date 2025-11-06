package JMC.car.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "vehiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean es_rural;
    private boolean es_manual;
    private Long cantidad_pasajeros;
    private float ruido;
    private float precio;
    private Long propulsion;
    private String nombre_vehiculo;

    Vehiculo(boolean es_rural, boolean es_manual, Long cantidad_pasajeros, float ruido, float precio, Long propulsion, String nombre_vehiculo){
        this.es_rural = es_rural;
        this.es_manual = es_manual;
        this.cantidad_pasajeros = cantidad_pasajeros;
        this.ruido = ruido;
        this.precio = precio;
        this.propulsion = propulsion;
        this.nombre_vehiculo = nombre_vehiculo;

    }
}
