package JMC.car.dto;

public class VehiculoSearchRequest {
    private boolean esRural;
    private boolean esManual;
    private int cantidadPasajeros;
    private double ruido;
    private double precio;
    private int propulsion;

    public boolean isEsRural() { return esRural; }
    public void setEsRural(boolean esRural) { this.esRural = esRural; }

    public boolean isEsManual() { return esManual; }
    public void setEsManual(boolean esManual) { this.esManual = esManual; }

    public int getCantidadPasajeros() { return cantidadPasajeros; }
    public void setCantidadPasajeros(int cantidadPasajeros) { this.cantidadPasajeros = cantidadPasajeros; }

    public double getRuido() { return ruido; }
    public void setRuido(double ruido) { this.ruido = ruido; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getPropulsion() { return propulsion; }
    public void setPropulsion(int propulsion) { this.propulsion = propulsion; }
}

