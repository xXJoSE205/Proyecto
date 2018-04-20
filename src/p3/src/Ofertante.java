package p3.src;

import java.io.Serializable;

/**
 * Esta clase contiene la informacion de una Ofertante
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class Ofertante extends Cliente implements Serializable {
    /** Cargo pendiente*/
    private double cargo = 0;
    /** Cadena con las modificaciones propuestas*/
    private String modificaciones = null;

    /**
     * Constructor de Ofertante, se llama al constructor de Cliente
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param nif Numero de Identificacion Fiscal
     * @param password Contrasena de acceso
     * @param tarjeta Cadena con el numero de la tarjeta
     */
    public Ofertante(String nombre, String apellidos, String nif, String password, String tarjeta)
            throws NullPointerException{
        super(nombre, apellidos, nif, password, tarjeta);
    }

    /**
     * Devuelve al cargo total pendiente
     *
     * @return double, cantidad pendiente de cargo
     */
    public double getCargo() {
        return cargo;
    }

    /**
     * Modifica el cargo pendiente, suma la cantidad al cargo
     *
     * @param cantidad double con la cantidad a anadir, positiva o negativa
     */
    public void setCargo(double cantidad) {
        this.cargo += cantidad;
    }

    /**
     * Devuelve las modificaciones propuestas
     *
     * @return Cadena con las modificaciones propuestas
     */
    public String getModificaciones() {
        return modificaciones;
    }

    /**
     * Anade modificaciones a las anteriores o establece unas nuevas
     *
     * @param modificaciones Cadena con las modificaciones a anadir
     * @param anadir boolean, true si se quieren anadir, false si se quiere establecer
     * @throws NullPointerException Si la cadena de modificaciones es null
     */
    public void anadirModificaciones(String modificaciones, boolean anadir) throws NullPointerException{
        if(modificaciones==null){
            throw new NullPointerException("Modificaciones null");
        }
        if(anadir){
            this.modificaciones += modificaciones;
        }else {
            this.modificaciones = modificaciones;
        }
    }

    /**
     * Quita las modificaciones poniendo el campo a null
     */
    public void quitarModificaciones(){
        this.modificaciones = null;
    }

    /**
     * Pasa toda la informacion del ofertante en una cadena
     *
     * @return Cadena con toda la informacion del ofertante
     */
    @Override
    public String toString() {
        return super.toString()+"\n\tCargo: "+cargo+"\n\t¿Modificaciones?: "+modificaciones;
    }
}
