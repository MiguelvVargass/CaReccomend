package com.recomendacionCarros.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Vehiculos")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean es_rural;
    private boolean es_manual;
    private int cantidad_pasajeros;
    private float ruido;
    private float precio;
    private int propulsion;
    private String nombre_vehiculo;

    public Car() {}


    public Car(boolean es_rural, boolean es_manual, int cantidad_pasajeros,
               float ruido, float precio, int propulsion, String nombre_vehiculo) {
        this.es_rural = es_rural;
        this.es_manual = es_manual;
        this.cantidad_pasajeros = cantidad_pasajeros;
        this.ruido = ruido;
        this.precio = precio;
        this.propulsion = propulsion;
        this.nombre_vehiculo = nombre_vehiculo;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean isEs_rural() { return es_rural; }
    public void setEs_rural(boolean es_rural) { this.es_rural = es_rural; }

    public boolean isEs_manual() { return es_manual; }
    public void setEs_manual(boolean es_manual) { this.es_manual = es_manual; }

    public int getCantidad_pasajeros() { return cantidad_pasajeros; }
    public void setCantidad_pasajeros(int cantidad_pasajeros) { this.cantidad_pasajeros = cantidad_pasajeros; }

    public float getRuido() { return ruido; }
    public void setRuido(float ruido) { this.ruido = ruido; }

    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }

    public int getPropulsion() { return propulsion; }
    public void setPropulsion(int propulsion) { this.propulsion = propulsion; }

    public String getNombre_vehiculo() { return nombre_vehiculo; }
    public void setNombre_vehiculo(String nombre_vehiculo) { this.nombre_vehiculo = nombre_vehiculo; }
}
