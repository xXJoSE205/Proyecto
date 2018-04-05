/**
 * Esta clase contiene la información de un Comentario
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

import java.io.Serializable;

public class Comentario extends Opinion implements Serializable {
    /** Texto del comentario*/
    private String texto;

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
}
