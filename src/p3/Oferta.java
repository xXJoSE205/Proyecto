package p3;

import java.time.LocalDate;

public class Oferta extends Inmueble{
    private double precio;
    private boolean reservado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean vacacional;
    private double fianza;
    private boolean disponible;

    public Oferta(int nHabitaciones, int nBanos, String dimensiones, String direccion, String planta, Boolean ascensor, double precio, boolean reservado, LocalDate fechaInicio, LocalDate fechaFin, boolean vacacional, double fianza, boolean disponible) {
        super(nHabitaciones, nBanos, dimensiones, direccion, planta, ascensor);
        this.precio = precio;
        this.reservado = reservado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vacacional = vacacional;
        this.fianza = fianza;
        this.disponible = disponible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isVacacional() {
        return vacacional;
    }

    public void setVacacional(boolean vacacional) {
        this.vacacional = vacacional;
    }

    public double getFianza() {
        return fianza;
    }

    public void setFianza(double fianza) {
        this.fianza = fianza;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    /*
    Implementar
     */
    public void aprobarOferta(){

    }

    public void rechazarOferta(){

    }

    public boolean modificarOferta(){
        return true;
    }
}
