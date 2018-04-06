/**
 * Esta clase contiene la información de un Demandante
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

import java.io.Serializable;

public class Demandante extends Cliente implements Serializable {
    /** Si esta bloqueado o no*/
    private boolean bloqueado = false;
    /** Si tiene una reserva activa o no*/
    private boolean reservaActiva = false;
    /** Reserva si la tuviese*/
    private Reserva reserva = null;

    /**
     * Constructor de Cliente, llama al constructor de Cliente
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param nif Numero de Identificacion Fiscal
     * @param password Contraseña de acceso
     * @param tarjeta Cadena con el numero de la tarjeta
     */
    public Demandante(String nombre, String apellidos, String nif, String password, String tarjeta)
            throws NullPointerException{
        super(nombre, apellidos, nif, password, tarjeta);
    }

    /**
     * Muestra si el demandante esta bloqueado o no
     *
     * @return boolean, true si esta bloqueado, false en caso contrario
     */
    public boolean isBloqueado() {
        return bloqueado;
    }

    /**
     * Bloquea al demandante
     *
     * @return boolean, true si se bloquea correctamente, false si ya estaba bloqueado
     */
    public boolean bloquear() {
        if (!bloqueado){
            bloqueado = true;
            return true;
        }
        return false;
    }

    /**
     * Muestra si el demandante tiene una reserva activa o no
     *
     * @return boolean, true si tiene una reserva activa, false en caso contrario
     */
    public boolean isReservaActiva() {
        return reservaActiva;
    }

    /**
     * Modifica la reserva activa del demandante
     *
     * @param reserva boolean, true si va a reservar una oferta, false en caso contrario
     */
    public void setReservaActiva(boolean reserva) {
        this.reservaActiva = reserva;
    }

    /**
     * Devuelve la reserva del demandante
     *
     * @return Reserva del usuario, null si no tiene ninguna
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * Modifica la reserva del demandante
     *
     * @param reserva Reserva que se va a asignar
     * @throws NullPointerException Si la reserva es null
     */
    public void setReserva(Reserva reserva) throws NullPointerException{
        if(reserva==null){
            throw new NullPointerException("Reserva null");
        }
        this.reserva = reserva;
    }

    public boolean quitarReserva(){
        if(this.reserva==null){
            return false;
        }
        this.reserva = null;
        return true;
    }

    /**
     * Desbloquea un usuario bloqueado
     *
     * @return boolean, true si se ha desbloqueado al usuario, false si el usuario no estuviese bloqueado
     */
    public boolean desbloquear(){
        if(bloqueado){
            bloqueado=false;
            return true;
        }
        return false;
    }

    /**
     * Pasa toda la informacion del demandante en una cadena
     *
     * @return Cadena con toda la informacion del demandante
     */
    @Override
    public String toString() {
        return super.toString()+"\n\t¿Bloqueado?: "+bloqueado+"\n\t¿Reserva?: "+reservaActiva;
    }
}
