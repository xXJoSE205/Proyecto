/**
 * Esta clase contiene la información del Sistema
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

import java.io.Serializable;
import java.time.LocalDate;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import java.util.ArrayList;
import java.util.List;

public class Sistema implements Serializable {
    /** Cantidad ganada de las comisiones*/
    private double totalComisiones = 0;
    /** Lista de los clientes*/
    private List<Cliente> usuarios;
    /** Pasarela de pago externa*/
    private final TeleChargeAndPaySystem pasarelaPago;
    /** Lista de todos los inmuebles*/
    private List<Inmueble> inmuebles;
    /** Lista de todas las ofertas*/
    private List<Oferta> ofertas;
    /** Lista de todas las opiniones*/
    private List<Opinion> opiniones;
    /** Gerente de la empresa*/
    private final Gerente gerente = new Gerente("Señor", "Supremo", "SoyDios", "Apruebanos");

    /**
     * Constructor de Sistema
     *
     * @param pasarelaPago Pasarela de pago externa
     * @throws NullPointerException Si la pasalera de pago es null
     */
    public Sistema(TeleChargeAndPaySystem pasarelaPago) throws NullPointerException{
        if(pasarelaPago==null){
            throw new NullPointerException("Pasarela de pago null");
        }
        this.usuarios = new ArrayList<>();
        this.pasarelaPago = pasarelaPago;
        this.inmuebles = new ArrayList<>();
        this.ofertas = new ArrayList<>();
        this.opiniones = new ArrayList<>();
    }

    /**
     * Devuelve el total ganado con las comisoines
     *
     * @return double, cantidad ganada
     */
    public double getTotalComisiones() {
        return totalComisiones;
    }

    /**
     * Devuelve la lista de los clientes
     *
     * @return Lista con los clientes
     */
    public List<Cliente> getUsuarios() {
        return usuarios;
    }

    /**
     * Devuelve la pasarela de pago
     *
     * @return TeleChargeAndPaySystem, pasarela de pago externa
     */
    public TeleChargeAndPaySystem getPasarelaPago() {
        return pasarelaPago;
    }

    /**
     * Devuelve la lista de los inmuebles
     *
     * @return Lista con todos los inmuebles
     */
    public List<Inmueble> getInmuebles() {
        return inmuebles;
    }

    /**
     * Devuelve la lista con las ofertas
     *
     * @return Lista con todas las ofertas
     */
    public List<Oferta> getOfertas() {
        return ofertas;
    }

    /**
     * Devuelve la lista con todas las opiniones
     *
     * @return Lista con todas las opiniones
     */
    public List<Opinion> getOpiniones() {
        return opiniones;
    }

    /**
     * Añade una cantidad al total ganado con las comisiones
     *
     * @param comisiones Cantidad a añadir
     * @throws IllegalArgumentException Si la cantidad es menor que 0
     */
    public void setTotalComisiones(double comisiones) throws IllegalArgumentException{
        if(comisiones<0){
            throw new IllegalArgumentException("Comisiones menor que 0: "+comisiones);
        }
        this.totalComisiones += comisiones;
    }

    /**
     * Añade un cliente al sistema
     *
     * @param usuario Cliente a añadir
     * @return boolean, true si se añade correctamente, false en caso contrario
     * @throws NullPointerException Si el usuario es null
     */
    public boolean anadirUsuario(Cliente usuario) throws NullPointerException{
        if(usuario==null){
            throw new NullPointerException("Usuario null");
        }
        return usuarios.add(usuario);
    }

    /**
     * Añade un inmuelbe al sistema
     *
     * @param inmueble Inmueble a añadir
     * @return boolean, true si se añade correctamente, false en caso contrario
     * @throws NullPointerException Si el inmueble es null
     */
    public boolean anadirInmueble(Inmueble inmueble) throws NullPointerException {
        if (inmueble == null) {
            throw new NullPointerException("Inmueble null");
        }
        return inmuebles.add(inmueble);
    }

    /**
     * Añade una oferta al sistema
     *
     * @param oferta Oferta a añadir
     * @return boolean, true si se añade correctamente, false en caso contrario
     * @throws NullPointerException Si la oferta es null
     */
    public boolean anadirOferta(Oferta oferta) throws NullPointerException {
        if (oferta == null) {
            throw new NullPointerException("Oferta null");
        }
        return ofertas.add(oferta);
    }

    /**
     * Añade una opinion al sistema
     *
     * @param opinion Opinion a añadir
     * @return boolean, true si se añade correctamente, false en caso contrario
     * @throws NullPointerException Si la opinion es null
     */
    public boolean anadirOpinion(Opinion opinion) throws NullPointerException {
        if (opinion == null) {
            throw new NullPointerException("Opinion null");
        }
        return opiniones.add(opinion);
    }

    /**
     * Logea a un cliente en el sistema
     *
     * @param usuario Usuario que quiere logearse
     * @param nif Numero de Identifiacion Fiscal del usuario
     * @param password Contraseña del usuario
     * @return boolean, true si se logea correctamente
     * false si ya hay alguien logeado, si el usuario no esta registrado en el sistema
     * o si los datos son incorrectos
     * @throws NullPointerException Si algun argumento es null
     */
    public boolean login(Cliente usuario, String nif, String password) throws NullPointerException{
        if(usuario==null || nif==null || password==null){
            throw new NullPointerException("Usuario, nif o password null");
        }
        if(usuarios.contains(usuario)) {
            for(Cliente x: usuarios) {
                if(x.isLogeado() || this.gerente.isLogeado()) {
                    return false;
                }
            }
            if ((usuario.getNif().equals(nif)) && (usuario.getPassword().equals(password))) {
                usuario.setLogeado(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Logea al gerente de la empresa
     *
     * @param nif Numero de Identificacion Fiscal del gerente
     * @param password Contraseña del gerente
     * @return boolean, true si se logea correctamente
     * false si ya hay alguien logeado o si los datos son incorrectos
     * @throws NullPointerException si algun argumento es null
     */
    public boolean login(String nif, String password) throws NullPointerException{
        if(nif==null || password==null) {
            throw new NullPointerException("Usuario, nif o password null");
        }
        for(Cliente x: this.usuarios) {
            if(x.isLogeado() || gerente.isLogeado()) {
                return false;
            }
        }
        if ((gerente.getNif().equals(nif)) && (gerente.getPassword().equals(password))) {
            gerente.setLogeado(true);
            return true;
        }
        return false;
    }

    /**
     * Desconecta a un usuario del sistema
     *
     * @param usuario usuario que quiere desconectarse
     * @return boolean, true si se desconecta correctamente
     * false si no esta registrado en el sistema o si no esta logeado
     * @throws NullPointerException Si el usuario es null
     */
    public boolean logout(Cliente usuario) throws NullPointerException{
        if(usuario==null){
            throw new NullPointerException("Usuario null");
        }
        if(usuarios.contains(usuario)) {
            if(usuario.isLogeado()){
                usuario.setLogeado(false);
                return true;
            }
        }
        return false;
    }

    /**
     * Desconecta al gerente del sistema
     *
     * @return boolean, true si se desconecta correctamente, false si no estaba logeado
     */
    public boolean logout(){
        if(gerente.isLogeado()){
            gerente.setLogeado(false);
            return true;
        }
        return false;
    }

    /**
     * Comprueba la validez de las reservas activas de todas las ofertas
     * La fecha de las ofertas no puede superar los cinco dias
     */
    public void comprobarReservas(){
        LocalDate fecha;
        for(Oferta o: ofertas){
            if(o.isReservado()){
                fecha = o.getReserva().getFechaInicio();
                fecha = fecha.plusDays(5);
                if(fecha.isEqual(LocalDate.now()) || fecha.isBefore(LocalDate.now())){
                    o.cancelarReserva();
                }
            }
        }
    }

    /**
     * Realiza una busqueda con filtros en las viviendas
     *
     * @param nHab numero de habitaciones de la vivienda, -1 si no se quiere filtrar por el numero de habitaciones
     * @param nBan numero de baños de la vivienda, -1 si no se quiere filtrar por el numero de baños
     * @param dim dimension de la vivienda, -1 si no se quiere filtrar por las dimensiones
     * @param planta planta de la vivienda, -1 si no se quiere filtrar por la planta
     * @param ascensor existencia de ascensor o no en la vivienda
     * @param dir direccion de la vivienda, null si no se quiere filtrar por direccion
     * @return list, lista con las viviendas obtenidas aplicando los filtros
     */
    public List<Inmueble> buscar(int nHab, int nBan, int dim, int planta, boolean ascensor, String dir){
        List<Inmueble> busqueda = new ArrayList<>();
        for(Inmueble inmueble: inmuebles){
            if(inmueble.getnHabitaciones()==nHab && nHab>-1){
                busqueda.add(inmueble);
            } else {
                busqueda.add(inmueble);
            }
        }for (Inmueble inmueble:busqueda){
            if(inmueble.getnBanos()!=nBan && nBan>-1){
                busqueda.remove(inmueble);
            }
            if(inmueble.getDimensiones()<dim && dim>-1){
                busqueda.remove(inmueble);
            }
            if(inmueble.getPlanta()!=planta && planta>-1){
                busqueda.remove(inmueble);
            }
            if(inmueble.getAscensor()!=ascensor){
                busqueda.remove(inmueble);
            }
            if(!(inmueble.getDireccion().equals(dir)) && dir!=null){
                busqueda.remove(inmueble);
            }
        }
        return busqueda;
    }

    /**
     * Realiza una busqueda con filtros para obtener todas las ofertas acordes a esos filtros
     *
     * @param nHab numero de habitaciones de la vivienda, -1 si no se quiere filtrar por el numero de habitaciones
     * @param nBan numero de baños de la vivienda, -1 si no se quiere filtrar por el numero de baños
     * @param dim dimension de la vivienda, -1 si no se quiere filtrar por las dimensiones
     * @param planta planta de la vivienda, -1 si no se quiere filtrar por la planta
     * @param ascensor existencia de ascensor o no en la vivienda
     * @param dir direccion de la vivienda, null si no se quiere filtrar por direccion
     * @param precio precio maximo de la vivienda
     * @param vacacional si es una vivienda vacacional o no
     * @return list, lista con las ofertas obtenidas aplicando los filtros, null en caso de que el cliente no este logeado
     * @throws NullPointerException si el cliente es null
     */
    public List<Oferta> avanzada(int nHab, int nBan, int dim, int planta, boolean ascensor, String dir, double precio,
                                 boolean vacacional, Cliente cliente){
        List<Oferta> ofertas = new ArrayList<>();
        List<Inmueble> aux = new ArrayList<>();
        if(cliente==null){
            throw new NullPointerException("Cliente null");
        }
        if(!cliente.isLogeado()){
            return null;
        }
        aux=buscar(nHab,nBan,dim,planta,ascensor,dir);
        for(Inmueble inmueble : aux){
            for(Oferta oferta:inmueble.getOfertas()){
                if(oferta.getEstado()==Estado.DISPONIBLE){
                    ofertas.add(oferta);
                }
            }
        }
        for(Oferta oferta: ofertas){
            if(oferta.getPrecio()>precio && precio>-1){
                ofertas.remove(oferta);
            }
            if(oferta.isVacacional()!=vacacional){
                ofertas.remove(oferta);
            }
        }
        return ofertas;
    }

    /**
     * Realiza el alquiler de un inmueble
     *
     * @param demandante demandante que quiere realizar el alquiler
     * @param oferta oferta de la que se quiere realizar el alquiler
     * @return true en caso de exito, false en caso de error
     * @throws NullPointerException si el demandante o la oferta es NULL
     */
    public boolean alquilar(Demandante demandante, Oferta oferta) throws OrderRejectedException, InvalidCardNumberException,
            FailedInternetConnectionException {
        double precio;
        if (demandante == null || oferta == null) {
            throw new NullPointerException("Demandante u oferta NULL");
        }
        if (oferta.getEstado() != Estado.DISPONIBLE) {
            return false;
        }
        pasarelaPago.charge(demandante.getTarjeta(),"Alquiler de vivienda", oferta.getPrecio());
        if (demandante.isReservaActiva() && oferta.isReservado()) {
            if (oferta.getReserva().getUsuario().equals(demandante)) {
                if (oferta.isVacacional()) {
                    setTotalComisiones(oferta.getPrecio() * 0.02 + getTotalComisiones());
                } else {
                    setTotalComisiones(oferta.getPrecio() * 0.01 + getTotalComisiones());
                }
                oferta.setEstado(Estado.NO_DISPONIBLE);

                return true;

            }
            return false;
        } else if (oferta.isReservado()) {
            return false;
        }
        if (oferta.isVacacional()) {
            setTotalComisiones(oferta.getPrecio() * 0.02 + getTotalComisiones());
        } else {
            setTotalComisiones(oferta.getPrecio() * 0.01 + getTotalComisiones());
        }
        oferta.setEstado(Estado.NO_DISPONIBLE);
        return true;

    }
}
