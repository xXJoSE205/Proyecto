/**
 * Esta clase contiene la información de un Inmueble
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

import java.util.ArrayList;
import java.util.List;

public class Inmueble {
    /** Numero de habitaciones*/
    private int nHabitaciones;
    /** Numero de baños*/
    private int nBanos;
    /** Dimensiones en metros cuadrados*/
    private int dimensiones;
    /** Nombre de la calle y el numero*/
    private String direccion;
    /** Planta en la que se encuentra*/
    private int planta;
    /** Si hay o no ascensor en el edificio*/
    private Boolean ascensor;
    /** Dueño del inmueble*/
    private Ofertante dueno;
    /** Lista de ofertas del inmueble*/
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
     * @param dueno Dueño del inmueble
     * @throws NullPointerException si la direccion o el dueño es null
     * @throws IllegalArgumentException si las habitaciones, baños, dimensione son menores que 1 o si la planta es menor que 0
     */
    public Inmueble(int nHabitaciones, int nBanos, int dimensiones, String direccion, int planta, Boolean ascensor,
                    Ofertante dueno) throws NullPointerException, IllegalArgumentException{
        if(direccion==null || dueno==null){ throw new NullPointerException("Direccion o dueño null"); }
        if(nHabitaciones<1 || nBanos<1 || dimensiones<1 || planta<0){
            throw new IllegalArgumentException("Numero de habbitaciones, baños o dimesiones menores que 1"
                    +" o planta menor que 0: "+nHabitaciones+", "+nBanos+", "+dimensiones+", "+planta);
        }
        this.nHabitaciones = nHabitaciones;
        this.nBanos = nBanos;
        this.dimensiones = dimensiones;
        this.direccion = direccion;
        this.planta = planta;
        this.ascensor = ascensor;
        this.dueno = dueno;
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
    public int getDimensiones() {
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

    /**
     * Devuelve las ofertas correspondientes al inmueble
     *
     * @return Lista con las ofertas
     */
    public List<Oferta> getOfertas() {
        return ofertas;
    }

    /**
     * Añade una oferta a un inmueble
     *
     * @param oferta oferta que se quiere añadir
     * @return boolean, true si se añade la oferta, false en caso contrario
     * @throws NullPointerException Si la oferta es null
     */
    public boolean anadirOferta(Oferta oferta) throws NullPointerException{
        if(oferta==null){
            throw new NullPointerException("Oferta null");
        }
        return ofertas.add(oferta);
    }
}
