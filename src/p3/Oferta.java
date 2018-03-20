/**
 * Esta clase contiene la información de una Oferta
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3;

import java.time.LocalDate;

public class Oferta{
    /** Precio de la oferta*/
    private double precio;
    /** Si esta reservada o no*/
    private boolean reservado = false;
    /** Fecha de inicio de la oferta*/
    private LocalDate fechaInicio;
    /** Fecha final de la oferta*/
    private LocalDate fechaFin;
    /** Si la oferta es vacacional o no*/
    private boolean vacacional;
    /** Fianza de la oferta*/
    private double fianza;
    /** Si esta disponible la oferta o no*/
    private boolean disponible;
    /** Puntero a Reserva, si esta reservada*/
    private Reserva reserva = null;

    /**
     * Constructor de Oferta
     *
     * @param precio Precio de la oferta
     * @param fechaInicio Fecha de inicio de la oferta
     * @param fechaFin Fecha final de la oferta
     * @param vacacional Boolean, true si es vacacional, false en caso contrario
     * @param fianza Fianza de la oferta
     */
    public Oferta(double precio, LocalDate fechaInicio, LocalDate fechaFin, boolean vacacional, double fianza) {
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vacacional = vacacional;
        this.fianza = fianza;
    }

    /**
     * Devuelve el precio de la oferta
     *
     * @return double, precio de la oferta
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio de la oferta
     *
     * @param precio double, precio nuevo
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Muestra si tiene esta reservado o no
     *
     * @return boolean, true si esta reservado, false en caso contrario
     */
    public boolean isReservado() {
        return reservado;
    }

    /**
     * Reserva o quita la reserva
     *
     * @param reservado boolean, true si se quiere reserva, false en caso contrario
     */
    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    /**
     * Devuelve la fecha de inicio de la oferta
     *
     * @return LocalDate, fecha de inicio
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Modifica la fecha de inicio de la oferta
     *
     * @param fechaInicio Nueva fecha de inicio
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Devuelve la fecha final de la oferta
     *
     * @return LocalDate, fecha final
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Modifica la fecha final de la oferta
     *
     * @param fechaFin Nueva fecha final
     */
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
        Reserva r = new Reserva(LocalDate.now());
        this.setReservado(true);
        this.reserva = r;
    }

    public void quitarReserva() {
        this.setReservado(false);
        this.reserva = null;
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
