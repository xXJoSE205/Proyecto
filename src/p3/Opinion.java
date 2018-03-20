/**
 * Esta clase contiene la información de una Opinion
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

import java.util.ArrayList;
import java.util.List;

public abstract class Opinion {
    private Demandante autor;
    private List<Comentario> comentarios;

    public Opinion(Demandante autor) {
        this.autor = autor;
        this.comentarios = new ArrayList<>();
    }

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
