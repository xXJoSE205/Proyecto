/**
 * Esta clase contiene la información de una Opinion
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

public abstract class Opinion {
    private Demandante autor;

    public Opinion(Demandante autor) {
        this.autor = autor;
    }

    public Demandante getAutor() {
        return autor;
    }
}
