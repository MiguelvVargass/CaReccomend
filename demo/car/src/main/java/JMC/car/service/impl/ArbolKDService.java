package JMC.car.service.impl;

import JMC.car.Entity.Vehiculo;

import java.util.*;

public class ArbolKDService {

    private static final int K = 6; // dimensiones del vector de características (sin id)

    // Nodo del árbol KD
    private static class NodoKD {
        double[] punto;
        Vehiculo vehiculo;
        NodoKD izquierda, derecha;
        int dimension;

        public NodoKD(double[] punto, Vehiculo vehiculo, int dimension) {
            this.punto = punto;
            this.vehiculo = vehiculo;
            this.dimension = dimension;
        }
    }

    private NodoKD raiz;

    // Construir a partir de un array de Vehiculo
    public void construirDesdeArray(Vehiculo[] vehiculos) {
        List<Map.Entry<double[], Vehiculo>> data = new ArrayList<>();
        if (vehiculos == null) {
            this.raiz = null;
            return;
        }
        for (Vehiculo v : vehiculos) {
            if (v == null) continue;
            data.add(new AbstractMap.SimpleEntry<>(toVector(v), v));
        }
        this.raiz = construirRecursivo(data, 0);
    }

    // Convierte un Vehiculo a un vector double[] de tamaño K
    public double[] toVector(Vehiculo v) {
        // Usar los getters generados por Lombok; los campos en la entidad usan guiones bajos
        double esRural = (v.isEs_rural() ? 1.0 : 0.0);
        double esManual = (v.isEs_manual() ? 1.0 : 0.0);
        double cantidadPasajeros = (v.getCantidad_pasajeros() != null) ? v.getCantidad_pasajeros().doubleValue() : 0.0;
        double ruido = v.getRuido();
        double precio = v.getPrecio();
        double propulsion = (v.getPropulsion() != null) ? v.getPropulsion().doubleValue() : 0.0;

        return new double[]{
                esRural,
                esManual,
                cantidadPasajeros,
                ruido,
                precio,
                propulsion
        };
    }

    // Construcción recursiva del KD-Tree
    private NodoKD construirRecursivo(List<Map.Entry<double[], Vehiculo>> puntos, int profundidad) {
        if (puntos == null || puntos.isEmpty()) return null;

        int dim = profundidad % K;

        puntos.sort(Comparator.comparingDouble(p -> p.getKey()[dim]));
        int mediana = puntos.size() / 2;

        NodoKD nodo = new NodoKD(puntos.get(mediana).getKey(), puntos.get(mediana).getValue(), dim);

        nodo.izquierda = construirRecursivo(new ArrayList<>(puntos.subList(0, mediana)), profundidad + 1);
        nodo.derecha = construirRecursivo(new ArrayList<>(puntos.subList(mediana + 1, puntos.size())), profundidad + 1);

        return nodo;
    }

    // Buscar vehículo más cercano a un vector objetivo
    public Vehiculo buscarMasCercano(double[] puntoObjetivo) {
        if (raiz == null) return null;
        NodoKD mejor = buscarRecursivo(raiz, puntoObjetivo, raiz, distanciaEuclidiana(raiz.punto, puntoObjetivo));
        return mejor != null ? mejor.vehiculo : null;
    }

    private NodoKD buscarRecursivo(NodoKD nodo, double[] objetivo, NodoKD mejor, double mejorDistancia) {
        if (nodo == null) return mejor;

        double distanciaActual = distanciaEuclidiana(nodo.punto, objetivo);
        NodoKD nuevoMejor = mejor;
        double nuevaMejorDistancia = mejorDistancia;

        if (distanciaActual < mejorDistancia) {
            nuevoMejor = nodo;
            nuevaMejorDistancia = distanciaActual;
        }

        int dim = nodo.dimension;
        NodoKD siguiente = (objetivo[dim] < nodo.punto[dim]) ? nodo.izquierda : nodo.derecha;
        NodoKD otroLado = (objetivo[dim] < nodo.punto[dim]) ? nodo.derecha : nodo.izquierda;

        NodoKD candidato = buscarRecursivo(siguiente, objetivo, nuevoMejor, nuevaMejorDistancia);
        if (candidato != null) {
            double distCandidato = distanciaEuclidiana(candidato.punto, objetivo);
            if (distCandidato < nuevaMejorDistancia) {
                nuevoMejor = candidato;
                nuevaMejorDistancia = distCandidato;
            }
        }

        // Comprobar si necesitamos buscar en el otro lado
        if (Math.abs(objetivo[dim] - nodo.punto[dim]) < nuevaMejorDistancia) {
            NodoKD otro = buscarRecursivo(otroLado, objetivo, nuevoMejor, nuevaMejorDistancia);
            if (otro != null) {
                double distOtro = distanciaEuclidiana(otro.punto, objetivo);
                if (distOtro < nuevaMejorDistancia) {
                    nuevoMejor = otro;
                    nuevaMejorDistancia = distOtro;
                }
            }
        }

        return nuevoMejor;
    }

    private double distanciaEuclidiana(double[] a, double[] b) {
        if (a == null || b == null) return Double.MAX_VALUE;
        double suma = 0;
        for (int i = 0; i < K; i++) {
            double ai = (i < a.length) ? a[i] : 0.0;
            double bi = (i < b.length) ? b[i] : 0.0;
            double diff = ai - bi;
            suma += diff * diff;
        }
        return Math.sqrt(suma);
    }

    // Helper: convierte parámetros sueltos a vector y busca
    public Vehiculo buscarMasCercanoPorParametros(boolean esRural,
                                                  boolean esManual,
                                                  int cantidadPasajeros,
                                                  double ruido,
                                                  double precio,
                                                  int propulsion) {
        double[] objetivo = new double[]{
                esRural ? 1.0 : 0.0,
                esManual ? 1.0 : 0.0,
                (double) cantidadPasajeros,
                ruido,
                precio,
                (double) propulsion
        };
        return buscarMasCercano(objetivo);
    }
}
