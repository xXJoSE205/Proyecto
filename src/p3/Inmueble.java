/**
 * Esta clase contiene la información de un Inmueble
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

import java.util.ArrayList;
import java.util.List;

public class Inmueble {
    /** Numero de habitaciones*/
    private int nHabitaciones;
    /** Numero de baños*/
    private int nBanos;
    /** Dimensiones en metros cuadrados*/
    private String dimensiones;
    /** Nombre de la calle y el numero*/
    private String direccion;
    /** Planta en la que se encuentra*/
    private int planta;
    /** Si hay o no ascensor en el edificio*/
    private Boolean ascensor;
    private List<Oferta> ofertas;

    /**
     * Constructor de Inmueble
     *
     * @param nHabitaciones Numero de habitaciones
     * @param nBanos Numero de baños
     * @param dimensiones Dimensiones con m2
     * @param direccion Calle y numero
     * @param planta Planta en la que se encuentra
     * @param ascensor True si hay ascensor, false en caso contrario
     */
    public Inmueble(int nHabitaciones, int nBanos, String dimensiones, String direccion, int planta, Boolean ascensor) {
        this.nHabitaciones = nHabitaciones;
        this.nBanos = nBanos;
        this.dimensiones = dimensiones;
        this.direccion = direccion;
        this.planta = planta;
        this.ascensor = ascensor;
        this.ofertas = new ArrayList<>();
    }

    /**
     * Devuelve el numero de habitaciones que hay
     *
     * @return Entero, numero de habitaciones
     */
    public int getnHabitaciones() {
        return nHabitaciones;
    }

    /**
     * Devuelve el numero de baños que hay
     *
     * @return Entero, numero de baños
     */
    public int getnBanos() {
        return nBanos;
    }

    /**
     * Devuelve las dimensiones del inmueble
     *
     * @return Cadena con las dimensiones
     */
    public String getDimensiones() {
        return dimensiones;
    }

    /**
     * Devuelve la direccion y el numero
     *
     * @return Cadena con la direccion y el numero
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Devuelve el numero de la planta
     *
     * @return Entero, numero de la planta
     */
    public int getPlanta() {
        return planta;
    }

    /**
     * Indica si hay ascensor o no
     *
     * @return boolean, true si hay ascensor, false en caso contrario
     */
    public Boolean getAscensor() {
        return ascensor;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public boolean anadirOferta(Oferta oferta){
        if(oferta==null){
            return false;
        }
        ofertas.add(oferta);
        return true;
    }
}
