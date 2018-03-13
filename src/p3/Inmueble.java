package p3;

public class Inmueble {
    private int nHabitaciones;
    private int nBanos;
    private String dimensiones;
    private String direccion;
    private String planta;
    private Boolean ascensor;


    public Inmueble(int nHabitaciones, int nBanos, String dimensiones, String direccion, String planta, Boolean ascensor) {
        this.nHabitaciones = nHabitaciones;
        this.nBanos = nBanos;
        this.dimensiones = dimensiones;
        this.direccion = direccion;
        this.planta = planta;
        this.ascensor = ascensor;
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
}

