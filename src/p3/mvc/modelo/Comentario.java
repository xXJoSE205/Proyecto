package p3.mvc.modelo;

import java.io.Serializable;

/**
 * Esta clase contiene la informacion de un Comentario
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class Comentario extends Opinion implements Serializable {
    /** Texto del comentario*/
    private final String texto;

    /**
     * Constructor de Comentario, llama al constructor de Opinion
     *
     * @param autor Autor del comentario
     * @param texto Texto con el comentario
     * @throws NullPointerException Si el texto es null
     */
    public Comentario(Demandante autor, String texto) throws NullPointerException{
        super(autor);
        if(texto==null){
            throw new NullPointerException("Texto null");
        }
        this.texto = texto;
    }

    /**
     * Devuelve el texto del comentario
     *
     * @return Cadena con el texto del comentario
     */
    public String getTexto() {
        return this.texto;
    }

    /**
     * Pasa toda la informacion del comentario en una cadena
     *
     * @return Cadena con toda la informacion del comentario
     */
    @Override
    public String toString() {
        return "\tTexto: "+texto;
    }
}
