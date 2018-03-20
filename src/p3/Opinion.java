/**
 * Esta clase contiene la información de una Opinion
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

import java.util.ArrayList;
import java.util.List;

public abstract class Opinion {
    /** Autor de la opinion*/
    private Demandante autor;
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

    public boolean anadirComentario(Comentario comentario){
        if(comentario==null){
            return false;
        }
        comentarios.add(comentario);
        return true;
    }

}
