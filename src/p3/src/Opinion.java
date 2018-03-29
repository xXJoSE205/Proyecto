/**
 * Esta clase contiene la información de una Opinion
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

import java.util.ArrayList;
import java.util.List;

public abstract class Opinion {
    /** Autor de la opinion*/
    private Demandante autor;
    /** Comentarios de la opinion*/
    private List<Comentario> comentarios;

    /**
     * Constructor de Opinion
     *
     * @param autor Autor del comentario
     * @throws NullPointerException si el autor es null
     */
    public Opinion(Demandante autor) {
        if(autor==null){
            throw new NullPointerException("Autor null");
        }
        this.autor = autor;
        this.comentarios = new ArrayList<>();
    }

    /**
     * Devuelve el autor de la opinion
     *
     * @return Demandante, autor de la opinion
     */
    public Demandante getAutor() {
        return autor;
    }

    /**
     * Añade un comentario a la opinion
     *
     * @param comentario Comentario a añadir
     * @return boolean, true si se añade coorectamente
     * @throws NullPointerException Si el comentario es null
     */
    public boolean anadirComentario(Comentario comentario){
        if(comentario==null){
            throw new NullPointerException("Comentario null");
        }
        comentarios.add(comentario);
        return true;
    }

}
