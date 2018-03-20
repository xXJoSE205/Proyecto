/**
 * Esta clase contiene la información de una Opinion
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

public abstract class Opinion {
    /** Autor de la opinion*/
    private Demandante autor;

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
    }

    /**
     * Devuelve el autor de la opinion
     *
     * @return Demandante, autor de la opinion
     */
    public Demandante getAutor() {
        return autor;
    }
}
