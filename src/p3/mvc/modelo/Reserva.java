package p3.mvc.modelo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Esta clase contiene la informacion de una Reserva
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class Reserva implements Serializable {
    /** Fecha de inicio de la reserva*/
    private final LocalDate fechaInicio;
    /** Usuario que realiza la reserva*/
    private final Demandante usuario;
    /** Oferta correspondiente a la reserva*/
    private Oferta oferta;

    /**
     * Constructor de Reserva, crea una reserva con la fecha actual
     *
     * @param usuario Demandante que realiza la reserva
     * @param oferta Oferta correspondiente a la reserva
     * @throws NullPointerException si el usuario o la oferta es null
     */
    public Reserva(Demandante usuario, Oferta oferta) throws NullPointerException{
        if(usuario==null || oferta==null){
            throw new NullPointerException("Usuario u oferta null");
        }
        this.fechaInicio = LocalDate.now();
        this.usuario = usuario;
        this.oferta = oferta;
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

    /**
     * Devuelve la oferta correspondiente a la reserva
     *
     * @return Oferta correspondiente
     */
    public Oferta getOferta() {
        return oferta;
    }

    /**
     * Pasa toda la informacion de la reserva en una cadena
     *
     * @return Cadena con toda la informacion de la reserva
     */
    @Override
    public String toString() {
        return "\tFecha de la reserva: "+fechaInicio;
    }
}
