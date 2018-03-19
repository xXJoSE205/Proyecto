/**
 * Esta clase contiene la información de una Valoracion
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

public class Valoracion extends Opinion {
    private int puntuacion;

    public Valoracion(Demandante autor) {
        super(autor);
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
