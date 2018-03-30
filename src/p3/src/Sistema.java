/**
 * Esta clase contiene la información del Sistema
 *
 * @author Jorge Mateo Segura y José Antonio Muñoz Ortega
 */
package p3.src;

import java.time.LocalDate;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    /** Cantidad ganada de las comisiones*/
    private double totalComisiones = 0;
    /** Lista de los clientes*/
    private List<Cliente> usuarios;
    /** Pasarela de pago externa*/
    private TeleChargeAndPaySystem pasarelaPago;
    /** Lista de todos los inmuebles*/
    private List<Inmueble> inmuebles;
    /** Lista de todas las ofertas*/
    private List<Oferta> ofertas;
    /** Lista de todas las opiniones*/
    private List<Opinion> opiniones;
    /** Gerente de la empresa*/
    private Gerente gerente;

    /**
     * Constructor de Sistema
     *
     * @param pasarelaPago Pasarela de pago externa
     * @throws NullPointerException Si la pasalera de pago o el gerente es null
     */
    public Sistema(TeleChargeAndPaySystem pasarelaPago, Gerente gerente){
        if(pasarelaPago==null || gerente==null){
            throw new NullPointerException("Pasarela de pago o gerente null");
        }
        this.usuarios = new ArrayList<>();
        this.pasarelaPago = pasarelaPago;
        this.inmuebles = new ArrayList<>();
        this.ofertas = new ArrayList<>();
        this.opiniones = new ArrayList<>();
        this.gerente = gerente;
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
    public void setTotalComisiones(double comisiones) {
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
    public boolean anadirUsuario(Cliente usuario){
        if(usuario==null){
            throw new NullPointerException("Usuario null");
        }
        return usuarios.add(usuario);
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
    public boolean login(Cliente usuario, String nif, String password){
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
    public boolean loginGerente(String nif, String password){
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
    public boolean logout(Cliente usuario){
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
    public boolean logoutGerente(){
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
     * @param nHab numero de habitaciones de la vivienda, -1 si no se quiere filtrar por el numero de habitaciones
     * @param nBan numero de baños de la vivienda, -1 si no se quiere filtrar por el numero de baños
     * @param dim dimension de la vivienda, -1 si no se quiere filtrar por las dimensiones
     * @param planta planta de la vivienda, -1 si no se quiere filtrar por la planta
     * @param ascensor existencia de ascensor o no en la vivienda
     * @param dir direccion de la vivienda, null si no se quiere filtrar por direccion
     * @return list, lista con las viviendas obtenidas aplicando los filtros
     */
    public List<Inmueble> buscar(int nHab, int nBan, int dim, int planta, boolean ascensor, String dir ){
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
     * @param nHab numero de habitaciones de la vivienda, -1 si no se quiere filtrar por el numero de habitaciones
     * @param nBan numero de baños de la vivienda, -1 si no se quiere filtrar por el numero de baños
     * @param dim dimension de la vivienda, -1 si no se quiere filtrar por las dimensiones
     * @param planta planta de la vivienda, -1 si no se quiere filtrar por la planta
     * @param ascensor existencia de ascensor o no en la vivienda
     * @param dir direccion de la vivienda, null si no se quiere filtrar por direccion
     * @param precio precio maximo de la vivienda
     * @param vacacional si es una vivienda vacacional o no
     * @return list, lista con las ofertas obtenidas aplicando los filtros
     */
    public List<Oferta> avanzada(int nHab, int nBan, int dim, int planta, boolean ascensor, String dir, double precio, boolean vacacional){
        List<Oferta> ofertas = new ArrayList<>();
        List<Inmueble> aux = new ArrayList<>();
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
}
