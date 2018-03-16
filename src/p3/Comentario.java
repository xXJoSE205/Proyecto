package p3;

public class Comentario extends Opinion {
    private String texto;

    public Comentario(Demandante autor, String texto) {
        super(autor);
        this.texto = texto;
    }

    public String getTexto() {
        return this.texto;
    }
}
