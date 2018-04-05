/**
 * Esta clase contiene la información de una Oferta
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

import java.io.Serializable;
import java.time.LocalDate;

public class Oferta implements Serializable {
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
    private Estado estado = Estado.PENDIENTE;
    /** Puntero a Reserva, si esta reservada*/
    private Reserva reserva = null;
    /** Inmueble al que pertenece la oferta*/
    private final Inmueble inmueble;

    /**
     * Constructor de Oferta
     *
     * @param precio Precio de la oferta
     * @param fechaInicio Fecha de inicio de la oferta
     * @param fechaFin Fecha final de la oferta
     * @param vacacional Boolean, true si es vacacional, false en caso contrario
     * @param fianza Fianza de la oferta
     * @throws NullPointerException Si alguna fecha es null
     * @throws IllegalArgumentException Si el precio o la fianza es menor que 0
     */
    public Oferta(double precio, LocalDate fechaInicio, LocalDate fechaFin, boolean vacacional, double fianza,
                  Inmueble inmueble) throws NullPointerException, IllegalArgumentException{
        if(fechaInicio==null || fechaFin==null || inmueble==null){
            throw new NullPointerException("Fecha inicio, fecha final o inmueble null");
        }
        if(precio<0 || fianza<0){
            throw new IllegalArgumentException("Precio o fianza menor que 0: "+precio+", "+fianza);
        }
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vacacional = vacacional;
        this.fianza = fianza;
        this.inmueble = inmueble;
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
     * @throws IllegalArgumentException Si el precio es menor que 0
     */
    public void setPrecio(double precio) throws IllegalArgumentException{
        if(precio<0){
            throw new IllegalArgumentException("Precio menor que 0");
        }
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
     * @throws NullPointerException Si la fecha de inicio es null
     */
    public void setFechaInicio(LocalDate fechaInicio) throws NullPointerException{
        if(fechaInicio==null){
            throw new NullPointerException("Fecha de inicio null");
        }
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
     * @throws NullPointerException Si la fecha final es null
     */
    public void setFechaFin(LocalDate fechaFin) throws NullPointerException{
        if(fechaFin==null){
            throw new NullPointerException("Fecha final null");
        }
        this.fechaFin = fechaFin;
    }

    /**
     * Si la oferta es de tipo vacacional o no
     *
     * @return boolean, true si es vacacional, false en caso contrario
     */
    public boolean isVacacional() {
        return vacacional;
    }

    /**
     * Modifica el tipo de la oferta, vacacional o no
     *
     * @param vacacional true si va a ser vacacional, false en caso contrario
     */
    public void setVacacional(boolean vacacional) {
        this.vacacional = vacacional;
    }

    /**
     * Devuelve la fianza de la oferta
     *
     * @return double, fianza de la oferta
     */
    public double getFianza() {
        return fianza;
    }

    /**
     * Modifica la fianza de la oferta
     *
     * @param fianza Cantidad de la fianza nueva
     * @throws IllegalArgumentException Si la fianza es menor que 0
     */
    public void setFianza(double fianza) throws IllegalArgumentException{
        if(fianza<0){
            throw new IllegalArgumentException("Precio menor que 0");
        }
        this.fianza = fianza;
    }

    /**
     * Devuelve el estado de la oferta
     *
     * @return Estado, estado en el que se encuentra la oferta
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Modifica el estado de la oferta
     *
     * @param estado Estado nuevo de la oferta
     * @throws IllegalArgumentException Si el estado no esta en la enumeracion
     */
    public void setEstado(Estado estado) throws IllegalArgumentException{
        if(estado.equals(Estado.DISPONIBLE)||estado.equals(Estado.PENDIENTE)||
                estado.equals(Estado.RECHAZADO)||estado.equals(Estado.NO_DISPONIBLE)){
            this.estado = estado;
            return;
        }
        throw new IllegalArgumentException("El estado no esta en la enumeracion");
    }

    /**
     * Devuelve la reserva activa si tiene alguna
     *
     * @return Reserva si tiene alguna, null en caso contrario
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * Devuelve el inmueble al que pertenece la oferta
     *
     * @return Inmueble, inmueble respectivo a la oferta
     */
    public Inmueble getInmueble() {
        return inmueble;
    }

    /**
     * Hace una reserva para la oferta y se la asigna
     *
     * @param usuario Demandante que realiza la reserva
     * @return boolean, true si se hace la reserva correctamente, false si ya existe una activa
     * @throws NullPointerException Si el usuario es null
     */
    public boolean reservar(Demandante usuario) throws NullPointerException{
        Reserva r;
        if(usuario==null){
            throw new NullPointerException("Usuario null");
        }
        if(usuario.isReservaActiva()){
            return false;
        }
        r = new Reserva(usuario);
        this.setReservado(true);
        this.reserva = r;
        usuario.setReservaActiva(true);
        return true;
    }

    /**
     * Cancela la reserva de la oferta y la pone a null
     *
     * @return boolean, true si se cancela correctamente, false si no habia ninguna oferta
     */
    public boolean cancelarReserva() {
        if(this.reserva==null){
            return false;
        }
        this.setReservado(false);
        this.reserva = null;
        return true;
    }

    /**
     * Acepta la oferta y cambia su estado a DISPONIBLE
     */
    public void aprobar(){
        estado = Estado.DISPONIBLE;
    }

    /**
     * Rechaza la oferta y cambia su estado a RECHAZADA
     */
    public void rechazar(){
        estado = Estado.RECHAZADO;
    }

    /**
     * Pasa toda la informacion de la oferta en una cadena
     *
     * @return Cadena con toda la informacion de la oferta
     */
    @Override
    public String toString() {
        return "\tPrecio: "+precio+"\n\t¿Reservado?: "+reservado+"\n\tFecha de inicio: "+fechaInicio
                +"\n\tFecha fin: "+fechaFin+"\n\t¿Vacacional?: "+vacacional+"\n\tFianza: "+fianza;
    }
}
