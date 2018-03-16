package p3;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Oferta{
    private double precio=0;
    private boolean reservado;
    private LocalDate fechaInicio=LocalDate.MIN;
    private LocalDate fechaFin=LocalDate.MIN;
    private boolean vacacional;
    private double fianza=0;
    private boolean disponible;
    private Reserva reserva;

    public Oferta(double precio, boolean reservado, LocalDate fechaInicio, LocalDate fechaFin, boolean vacacional, double fianza, boolean disponible) {
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

    public Reserva getReserva() {
        return reserva;
    }

    public void reservar() {
        Reserva r = new Reserva(LocalDateTime.now());
        this.setReservado(true);
        this.reserva = r;
    }
    /*
        Implementar
         */
    public void aprobarOferta(){
        if(precio>0 && fianza>0 && !fechaInicio.isEqual(LocalDate.MIN)){
            disponible=true;
            return;
        }
    }

    public void rechazarOferta(){


    }

    public boolean modificarOferta(){
        return true;
    }
}
