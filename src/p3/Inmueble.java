package p3;

import java.util.ArrayList;
import java.util.List;

public class Inmueble {
    private int nHabitaciones;
    private int nBanos;
    private String dimensiones;
    private String direccion;
    private String planta;
    private Boolean ascensor;
    private List<Oferta> ofertas;

    public Inmueble(int nHabitaciones, int nBanos, String dimensiones, String direccion, String planta, Boolean ascensor) {
        this.nHabitaciones = nHabitaciones;
        this.nBanos = nBanos;
        this.dimensiones = dimensiones;
        this.direccion = direccion;
        this.planta = planta;
        this.ascensor = ascensor;
        this.ofertas = new ArrayList<>();
    }

    public int getnHabitaciones() {
        return nHabitaciones;
    }

    public int getnBanos() {
        return nBanos;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPlanta() {
        return planta;
    }

    public Boolean getAscensor() {
        return ascensor;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public boolean anadirOferta(Oferta oferta){
        if(oferta==null){
            return false;
        }
        ofertas.add(oferta);
        return true;
    }
}
