/**
 * Esta clase contiene la información de un Comentario
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

public class Comentario extends Opinion {
    /** Texto del comentario*/
    private String texto;

    /**
     * Constructor de Comentario, llama al constructor de Opinion
     *
     * @param autor Autor del comentario
     * @param texto Texto con el comentario
     */
    public Comentario(Demandante autor, String texto) {
        super(autor);
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
