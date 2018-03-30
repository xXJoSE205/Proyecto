/**
 * Esta clase contiene la información de una Ofertante
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

public class Ofertante extends Cliente{
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
     * @param password Contraseña de acceso
     * @param tarjeta Cadena con el numero de la tarjeta
     */
    public Ofertante(String nombre, String apellidos, String nif, String password, String tarjeta) {
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
     * @param cantidad double con la cantidad a añadir, positiva o negativa
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
     * Añade modificaciones a las anteriores o establece unas nuevas
     *
     * @param modificaciones Cadena con las modificaciones a añadir
     * @param anadir boolean, true si se quieren añadir, false si se quiere establecer
     * @throws NullPointerException Si la cadena de modificaciones es null
     */
    public void anadirModificaciones(String modificaciones, boolean anadir) {
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
}
