package p3.src;

import java.io.Serializable;

/**
 * Esta clase contiene la informacion de una Valoracion
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class Valoracion extends Opinion  implements Serializable {
    /** Puntuacion de la opinion, entre 0 y 5*/
    private int puntuacion;

    /**
     * Constructor de una Valoracion
     *
     * @param autor Demandante que hace la puntuacion
     * @param puntuacion Puntuacion etre 0 y 5
     * @throws IllegalArgumentException Si la puntuacion esta fuera del rango
     */
    public Valoracion(Demandante autor, int puntuacion) throws NullPointerException, IllegalArgumentException{
        super(autor);
        if(puntuacion<0 || puntuacion>5){
            throw new IllegalArgumentException("Puntuacion fuera de rango <0-5>: "+puntuacion);
        }
        this.puntuacion = puntuacion;
    }

    /**
     * Devueve la puntuacion
     *
     * @return Entero, puntuacion entre 0 y 5
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Modifica la puntuacion
     *
     * @param puntuacion Puntuacion nueva entre 0 y 5
     * @throws IllegalArgumentException Si la puntuacion esta fuera del rango
     */
    public void setPuntuacion(int puntuacion) throws IllegalArgumentException{
        if(puntuacion<0 || puntuacion>5){
            throw new IllegalArgumentException("Puntuacion fuera de rango <0-5>: "+puntuacion);
        }
        this.puntuacion = puntuacion;
    }

    /**
     * Pasa toda la informacion de la valoracion en una cadena
     *
     * @return Cadena con toda la informacion de la valoracion
     */
    @Override
    public String toString() {
        return "\tPuntuacion: "+puntuacion;
    }
}
