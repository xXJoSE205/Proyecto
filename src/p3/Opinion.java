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
