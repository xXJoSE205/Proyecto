package p3;

import java.util.List;

public class Opinion {
    private String texto;
    private int puntuacion;
    private Opinion padre;
    private List<Opinion> respuestas;
    private Inmueble inmueble;

    public Opinion(String texto, int puntuacion) {
        this.texto = texto;
        this.puntuacion = puntuacion;
    }

    public String getTexto() {
        return texto;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public Opinion getPadre() {
        return padre;
    }

    public List<Opinion> getRespuestas() {
        return respuestas;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    private void setPadre(Opinion padre) {
        this.padre = padre;
    }

    public boolean anadirRespuesta(Opinion respuesta){
        if(respuestas.add(respuesta)){
            respuesta.setPadre(this);
            return true;
        }
        return false;
    }
}
