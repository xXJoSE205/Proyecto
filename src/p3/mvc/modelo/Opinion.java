package p3.mvc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene la informacion de una Opinion
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public abstract class Opinion implements Serializable {
    /** Autor de la opinion*/
    private final Demandante autor;
    /** Comentarios de la opinion*/
    private List<Comentario> comentarios;

    /**
     * Constructor de Opinion
     *
     * @param autor Autor del comentario
     * @throws NullPointerException si el autor es null
     */
    Opinion(Demandante autor) throws NullPointerException{
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
     * Anade un comentario a la opinion
     *
     * @param comentario Comentario a anadir
     * @return boolean, true si se anade coorectamente, false en caso contrario
     * @throws NullPointerException Si el comentario es null
     */
    public boolean anadirComentario(Comentario comentario) throws NullPointerException{
        if(comentario==null){
            throw new NullPointerException("Comentario null");
        }
        return comentarios.add(comentario);
    }

    /**
     * Devuelve la lista de respuestas al comentario
     * @return list, lista de comentarios
     */
    public List<Comentario> getComentarios(){
        return comentarios;
    }

}
