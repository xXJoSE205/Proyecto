/**
 * Esta clase contiene la información de una Reserva
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

import java.io.Serializable;
import java.time.LocalDate;

public class Reserva implements Serializable {
    /** Fecha de inicio de la reserva*/
    private final LocalDate fechaInicio;
    /** Usuario que realiza la reserva*/
    private final Demandante usuario;

    /**
     * Constructor de Reserva, crea una reserva con la fecha actual
     *
     * @param usuario Demandante que realiza la reserva
     * @throws NullPointerException si el usuario es null
     */
    public Reserva(Demandante usuario) throws NullPointerException{
        if(usuario==null){
            throw new NullPointerException("Usuario null");
        }
        this.fechaInicio = LocalDate.now();
        this.usuario = usuario;
    }

    /**
     * Devuelve la fecha en que se hizo la reserva
     *
     * @return LocalDate, fecha de la reserva
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Devuelve el usuario que hizo la reserva
     *
     * @return Demandante que hizo la reserva
     */
    public Demandante getUsuario() {
        return usuario;
    }
}
