/**
 * Esta clase contiene la información de un Demandante
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

public class Demandante extends Cliente {
    /** Deuda del demandante*/
    private double deuda = 0;
    /** Si esta bloqueado o no*/
    private boolean bloqueado = false;
    /** Si tiene una reserva activa o no*/
    private boolean reservaActiva = false;

    /**
     * Constructor de Cliente, llama al constructor de Cliente
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param nif Numero de Identificacion Fiscal
     * @param password Contraseña de acceso
     * @param tarjeta Cadena con el numero de la tarjeta
     */
    public Demandante(String nombre, String apellidos, String nif, String password, String tarjeta) {
        super(nombre, apellidos, nif, password, tarjeta);
    }

    /**
     * Devuelve la deuda que tiene el demandante
     *
     * @return double, cantidad de dinero que debe
     */
    public double getDeuda() {
        return deuda;
    }

    /**
     * Modifica la deuda del demandante
     *
     * @param deuda Cantidad que se quiere sumar o restar
     */
    public void setDeuda(double deuda) {
        this.deuda += deuda;
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
     * Bloquea o desbloquea al demandante
     *
     * @param bloqueado boolean, true si se quiere bloquear, false en caso contrario
     */
    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
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
     * Desbloquea un usuario bloqueado
     *
     * @return boolean, true en casa de que se haya desbloqueado el usuario, false en caso de que no estuviese bloqueado
     */
    public boolean desbloquearUsuario(){
        if(bloqueado==true){
            bloqueado=false;
            return true;
        }
        return false;
    }
}
