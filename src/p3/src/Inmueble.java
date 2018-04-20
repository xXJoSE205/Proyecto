package p3.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene la informacion de un Inmueble
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class Inmueble implements Serializable {
    /** Numero de habitaciones*/
    private final int nHabitaciones;
    /** Numero de banos*/
    private final int nBanos;
    /** Dimensiones en metros cuadrados*/
    private final int dimensiones;
    /** Nombre de la calle y el numero*/
    private final String direccion;
    /** Planta en la que se encuentra*/
    private final int planta;
    /** Si hay o no ascensor en el edificio*/
    private final Boolean ascensor;
    /** Dueno del inmueble*/
    private final Ofertante dueno;
    /** Lista de ofertas del inmueble*/
    private List<Oferta> ofertas;
    /** Lista de opiniones*/
    private List<Opinion> opiniones;

    /**
     * Constructor de Inmueble
     *
     * @param nHabitaciones Numero de habitaciones
     * @param nBanos Numero de banos
     * @param dimensiones Dimensiones con m2
     * @param direccion Calle y numero
     * @param planta Planta en la que se encuentra
     * @param ascensor True si hay ascensor, false en caso contrario
     * @param dueno Dueno del inmueble
     * @throws NullPointerException si la direccion o el dueno es null
     * @throws IllegalArgumentException si las habitaciones, banos, dimensione son menores que 1
     * o si la planta es menor que 0
     */
    public Inmueble(int nHabitaciones, int nBanos, int dimensiones, String direccion, int planta, Boolean ascensor,
                    Ofertante dueno) throws NullPointerException, IllegalArgumentException{
        if(direccion==null || dueno==null){ throw new NullPointerException("Direccion o dueno null"); }
        if(nHabitaciones<1 || nBanos<1 || dimensiones<1 || planta<0){
            throw new IllegalArgumentException("Numero de habbitaciones, banos o dimesiones menores que 1"
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
        this.opiniones = new ArrayList<>();
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
     * Devuelve el numero de banos que hay
     *
     * @return Entero, numero de banos
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
     * Devuelve el dueno del inmueble
     *
     * @return Ofertante, el dueno del inmueble
     */
    public Ofertante getDueno() {
        return dueno;
    }

    /**
     * Anade una oferta a un inmueble
     *
     * @param oferta oferta que se quiere anadir
     * @return boolean, true si se anade la oferta, false en caso contrario
     * @throws NullPointerException Si la oferta es null
     */
    public boolean anadirOferta(Oferta oferta) throws NullPointerException{
        if(oferta==null){
            throw new NullPointerException("Oferta null");
        }
        return ofertas.add(oferta);
    }

    /**
     * Anade una opinion al inmueble
     *
     * @param opinion Opinion que se quiere anadir
     * @return boolean, true si se anade la opinion, false en caso contrario
     * @throws NullPointerException Si la opinion es null
     */
    public boolean anadirOpinion(Opinion opinion) throws NullPointerException{
        if(opinion==null){
            throw new NullPointerException("Opinion null");
        }
        return opiniones.add(opinion);
    }

    /**
     * Pasa toda la informacion del inmueble en una cadena
     *
     * @return Cadena con toda la informacion del inmueble
     */
    @Override
    public String toString() {
        return "\tHabitaciones: "+nHabitaciones+"\n\tBanos: "+nBanos+"\n\tDimensiones: "+dimensiones
                +"m2\n\tDireccion: "+direccion+"\n\tPlanta: "+planta+"\n\t¿Ascensor?: "+ascensor;
    }
}
