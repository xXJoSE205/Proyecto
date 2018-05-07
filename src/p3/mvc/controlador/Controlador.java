package p3.mvc.controlador;

import p3.mvc.interfaz.GuiInmobiliaria;
import p3.mvc.modelo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene la funcionalidad del Controlador de la aplicacion
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class Controlador {
    /** GUI de la aplicacion*/
    private final GuiInmobiliaria gui;
    /** Sistema principal*/
    private final Sistema muzska;
    /** Cliente logueado*/
    private Cliente usr;
    /** Lista con los inmuebles buscados*/
    private List<Inmueble> busqueda;
    /** Lista con las ofertas buscadas*/
    private List<Oferta> avanzada;

    private List<Comentario> comentarios;
    /** Oferta seleccionada*/
    private Oferta oferta;
    /** Comentario seleccionado*/
    private Comentario comentario;
    /**Demandante seleccionado*/
    private Demandante demandante;
    /**Flag para saber el retorno de alquilar*/
    private int flag=0;
    /**
     * Constructor del Controlador
     *
     * @param gui GUI de la aplicacion
     * @param muzska Sistema a controlar
     */
    public Controlador(GuiInmobiliaria gui, Sistema muzska) {
        this.gui = gui;
        this.muzska = muzska;
        Demandante demandante = new Demandante("tony","Stark","78945612T","Ironman","7894789478947894");
        demandante.bloquear();
        muzska.anadirUsuario(demandante);
    }

    /**
     * Loguea en el sistema al usuario correspondiente y llama al GUI
     *
     * @param nif NIF del usuario a loguear
     * @param passwd Contrasena del usuario
     * @param option Opcion de logueo, Demandante, Ofertante o Gerente
     */
    public void login(String nif, String passwd, String option) {
        if(nif==null || passwd==null || option==null){
            this.gui.loginResult(false);
        }
        try {
            assert option != null;
            switch (option) {
                case "Demandante": {
                    List<Cliente> clientes = muzska.getUsuarios();
                    for (Cliente d : clientes) {
                        if (d instanceof Demandante) {
                            if (d.getNif().equals(nif)) {
                                if(muzska.login(d, nif, passwd)) {
                                    this.usr = d;
                                    this.gui.loginResult(true);
                                }else{
                                    this.gui.loginResult(false);
                                }
                            }
                        }
                    }
                    break;
                }
                case "Ofertante": {
                    List<Cliente> clientes = muzska.getUsuarios();
                    for (Cliente o : clientes) {
                        if (o instanceof Ofertante) {
                            if (o.getNif().equals(nif)) {
                                if(muzska.login(o, nif, passwd)) {
                                    this.usr = o;
                                    this.gui.loginResult(true);
                                }else{
                                    this.gui.loginResult(false);
                                }
                            }
                        }
                    }
                    break;
                }
                case "Gerente":
                    this.gui.loginResult(muzska.login(nif, passwd));
                    break;
                default:
                    this.gui.loginResult(false);
                    break;
            }
        }catch (Exception e){
            quitarLogin();
            this.gui.loginResult(false);
        }
    }

    /**
     * Realiza una busqueda basica y llama al GUI
     *
     * @param nHab Numero de habitaciones
     * @param nBan Numero de banos
     * @param dim Dimensiones en m2
     * @param planta Planta
     * @param ascensor Boolean, si hay ascensor o no
     * @param dir Direccion
     */
    public void buscar (int nHab, int nBan, int dim, int planta, boolean ascensor, String dir){
        String direccion;
        if(dir.length()>0) {
            direccion = dir;
        }else{
            direccion = null;
        }
            try {
                this.busqueda = muzska.buscar(nHab, nBan, dim, planta, ascensor, direccion);
                if (this.busqueda == null || this.busqueda.size()==0) {
                    this.gui.errorBusqueda("Error, no hay inmuebles que coincidan con las condiciones");
                } else {
                    this.gui.goBusquedaResultado();
                }
            } catch (Exception e) {
                this.gui.errorBusqueda(e.getMessage());
            }
    }

    /**
     * Llama al GUI para volver a la pantalla principal
     */
    public void volverLogin() {
        this.gui.volverLogin();
    }

    /**
     * Llama al GUI para ir a la pantalla de login
     */
    public void goLogin() {
        this.gui.goLogin();
    }

    /**
     * Llama al GUI para volver a la pantalla anterior de una busqueda
     */
    public void volverDeBusqueda() {
        this.gui.volverDeBusqueda(usr);
    }

    /**
     * Llama al GUI para volver a la pantalla de busqueda
     */
    public void volverBusqueda() {
        this.gui.volverBusqueda();
    }

    /**
     * Llama al GUI para ir a la pantalla de busqueda
     */
    public void goBusqueda() {
        this.gui.goBusqueda();
    }

    /**
     * Llama al GUI para guardar los datos del sistema
     */
    public void saveData() {
        this.gui.guardarDatos(this.muzska);
    }

    /**
     * Desloguea al usuario o gerente y llama al GUI
     */
    public void logout() {
        if(this.muzska.getGerente().isLogeado()) {
            this.gui.logout(this.muzska.logout());
        }else{
            for(Cliente c: this.muzska.getUsuarios()){
                if(c.isLogeado()){
                    this.gui.logout(this.muzska.logout(c));
                    quitarLogin();
                }
            }
            this.gui.logout(false);
        }
    }

    /**
     * Llama al GUI para ir a la pantalla de busqueda avanzada
     */
    public void goAvanzada(){
        this.gui.goAvanzada();
    }

    /**
     * Llama al GUI para volver a la pantalla de demandante
     */
    public void volverDemandante(){
        this.flag=0;
        this.gui.volverDemandante();
    }

    /**
     * Cancela la reserva del demandante logueado y llama al GUI
     */
    public void cancelarReserva(){
        if(usr instanceof Demandante){
            try{
                if(((Demandante) usr).isReservaActiva()) {
                    ((Demandante) usr).quitarReserva();
                    this.gui.cancelarReservaOK("Reserva cancelada");
                }else{
                    this.gui.cancelarReservaOK("Error, no hay reserva");
                }
            } catch (Exception e){
                this.gui.cancelarReservaOK(e.getMessage());
            }
        }
    }

    /**
     * Realiza una busqueda avanzada y llama al GUI
     *
     * @param nHab Numero de habitaciones
     * @param nBan Numero de banos
     * @param dim Dimensiones en m2
     * @param planta Planta
     * @param ascensor Boolean, si tiene ascensor o no
     * @param dir Direccion
     * @param precio Precio
     * @param vacacional Boolean, si es vacacional o no
     */
    public void avanzada(int nHab, int nBan, int dim, int planta, boolean ascensor, String dir, int precio, boolean vacacional){
        String direccion;
        if(dir.length()>0) {
            direccion = dir;
        }else{
            direccion = null;
        }
        this.avanzada = muzska.avanzada(nHab, nBan, dim, planta, ascensor, direccion, precio, vacacional, usr);
        if (this.avanzada.isEmpty()) {
            this.gui.avanzadaError("Error, no hay ofertas que coincidan con las condiciones");
        } else {
            this.gui.goRAvanzada();
        }
    }

    /**
     * Alquila una oferta con el Demandante logueado y llama al GUI
     * @param oferta Oferta a alquilar
     */
    public void alquilar(Oferta oferta){
        try{
            if(muzska.alquilar((Demandante)usr,oferta)){
                this.gui.alquilerOK("Alquiler realizado correctamente");
                if(flag==1){
                    ((Demandante) usr).setReservaActiva(false);
                    ((Demandante)usr).quitarReserva();
                }
                
            } else{
                this.gui.alquilerOK("Error al alquilar");
            }
        } catch (Exception e){
            this.gui.alquilerOK(e.getMessage());
        }
    }

    /**
     * Anade una valoracion a la oferta y llama al GUI
     * @param x Entero, valoracion de la oferta 1-5
     */
    public void valorar(int x){
        Valoracion valoracion = new Valoracion((Demandante)usr,x);
        try {
            if(oferta.anadirOpinion(valoracion)){
                this.gui.valoracionOK("Valoracion realizada correctamente");
            } else {
                this.gui.valoracionOK("Error al valorar");
            }
        } catch(Exception e){
            this.gui.valoracionOK(e.getMessage());
        }
    }

    /**
     * Llama al GUI para ir a la pantalla de anadir comentario
     * @param comentario Comentario al que se quiere anadir un comentario
     */
    public void anadirComentario(Comentario comentario){
        this.comentario=comentario;
        this.gui.goAnadirComentario();
    }

    /**
     * Llama al GUI para ir a la pantalla de crear comentario
     * @param oferta Oferta a la que se quiere anadir un comentario/valoracion
     */
    public void anadirComentario(Oferta oferta){
        this.oferta=oferta;
        this.gui.goCrearComentario();
    }

    /**
     * Pone a null el atributo usr
     */
    private void quitarLogin(){
        this.usr = null;
    }

    /**
     * Devuelve la lista con los resultados de la busqueda basica
     * @return Lista con los inmuebles encontrados
     */
    public List<Inmueble> getBusqueda(){return busqueda;}

    /**
     * Devuelve la lista con los resultados de la busqueda avanzada
     * @return Lista con las ofertas encontradas
     */
    public List<Oferta> getAvanzada(){return avanzada;}

    /**
     * Devuelve la lista con los comentarios de la oferta
     * @return Lista con los comentarios encontrados
     */
    public List<Comentario> getComentarios(){
        List<Comentario> lista= new ArrayList<>();
        for(Opinion o :oferta.getOpiniones()){
            if(o instanceof Comentario){
                lista.add((Comentario)o);
            }
        }
        return lista;
    }

    /**
     * Devuelve el usuario logueado
     * @return Cliente si hay un usario registrado, null en caso contrario
     */
    public Cliente getCliente() {
        return usr;
    }

    /**
     * LLama al GUI para ir a la pantalla de crear inmueble
     */
    public void goCrearInmueble() {
        this.gui.goCrearInmueble();
    }

    /**
     * LLama al GUI para ir a la pantalla de crear oferta
     */
    public void goCrearOferta(Inmueble inmueble) {
        this.gui.goCrearOferta(inmueble);
    }

    /**
     * LLama al GUI para volver a la pantalla de ofertante
     */
    public void volverOfertante(){
        this.gui.volverOfertante();
    }

    /**
     * LLama al GUI para volver a la pantalla de ver inmuebles
     */
    public void volverVerInmuebles(){
        this.gui.volverVerInmuebles();
    }

    /**
     * Crea un inmueble, lo anade al sistema y llama al GUI
     *
     * @param nHab Numero de habitaciones
     * @param nBanos Numero de banos
     * @param dim Dimensiones en m2
     * @param dir Direccion
     * @param planta Planta
     * @param ascensor Boolean, si tiene ascensor o no
     */
    public void crearInmueble(int nHab, int nBanos, int dim, String dir, int planta, boolean ascensor) {
        if(dir.length()>0){
            try {
                if (usr instanceof Ofertante) {
                    Inmueble inmueble = new Inmueble(nHab, nBanos, dim, dir, planta, ascensor, (Ofertante) usr);
                    if (muzska.anadirInmueble(inmueble)) {
                        this.gui.creadoOK("El inmueble se ha creado correctamente");
                    } else {
                        this.gui.creadoOK("Error al añadir el inmueble");
                    }
                }
            } catch (Exception e) {
                this.gui.creadoOK(e.getMessage());
            }
        }else {
            this.gui.creadoOK("La direccion no es valida");
        }
    }

    /**
     * LLama al GUI para ir a la pantalla de ver inmuebles
     */
    public void goVerInmuebles() {
        this.gui.goVerInmuebles();
    }

    public void goComentario(Oferta oferta){
        this.oferta=oferta;
        this.gui.goComentarios();
    }

    /**
     * Crea una oferta para el inmueble indicado y llama al GUI
     *
     * @param precio Precio
     * @param fIni Fecha de inicio
     * @param fFin Fecha final
     * @param vacacional Boolean, si es vacacional o no
     * @param fianza Fianza
     * @param inmueble Inmueble al que anadir la oferta
     */
    public void crearOferta(double precio, LocalDate fIni, LocalDate fFin, boolean vacacional
            , double fianza, Inmueble inmueble) {
        try {
            if (usr instanceof Ofertante) {
                Oferta oferta = new Oferta(precio, fIni, fFin, vacacional, fianza, inmueble);
                if(inmueble.anadirOferta(oferta)){
                    this.gui.creadaOK("La oferta se ha creado correctamente");
                }else{
                    this.gui.creadaOK("Error al añadir la oferta");
                }

            }
        }catch(Exception e){
            this.gui.creadaOK(e.getMessage());
        }
    }

    /**
     * Devuelve el sistema de la aplicacion
     * @return Sistema, Muzska
     */
    public Sistema getSistema() {
        return muzska;
    }

    /**
     * Devuelve la oferta seleccionada
     * @return Oferta seleccionada
     */
    public Oferta getOferta(){return oferta;}

    public void volverRAvanzada(int n){
        switch (n) {
            case 1:
                this.gui.volverRAvanzada();
                break;
            case 2:
                this.gui.volverRAvanzada2();
                break;
            case 3:
                this.gui.volverRAvanzada3();
                break;
            case 4:
                this.gui.volverRAvanzada4();
                break;
        }
    }

    /**
     * Llama al GUI para volver a la pantalla de busqueda avanzada
     */
    public void volverAvanzada(){
        this.gui.volverBAvanzada();
    }

    /**
     * Llama al GUI para ir a la pantalla de desbloquear usuario
     * @param demandante Demandante a desbloquear
     */
    public void goDesbloquearUsuarios(Demandante demandante) {
        this.demandante=demandante;
        this.gui.goDesbloquearUsuarios(demandante);
    }

    /**
     * Llama al GUI para ir a la pantalla de comprobar ofertas pendientes
     */
    public void goComprobarOfertas() {
        this.gui.goComprobarOfertas();
    }

    /**
     * Devuelve la lista de los usuarios bloqueados
     * @return Lista con los usuarios bloqueados
     */
    public List<Demandante> getUsuariosBloqueados() {
        List<Demandante> usuarios = new ArrayList<>();

        for(Cliente c: muzska.getUsuarios()){
            if(c instanceof Demandante){
                if(((Demandante) c).isBloqueado())
                    usuarios.add((Demandante)c);
            }
        }
        return usuarios;
    }

    /**
     * Llama la GUI para volver a la pantalla de gerente
     */
    public void volverGerente() {
        this.gui.volverGerente();
    }

    /**
     * Devuelve la lista de las ofertas pendientes
     * @return Lista con las ofertas pendientes
     */
    public List<Oferta> getOfertasPendientes() {
        List<Oferta> ofertas = new ArrayList<>();

        for(Inmueble i: muzska.getInmuebles()){
            for(Oferta o: i.getOfertas()){
                if(o.getEstado()==Estado.PENDIENTE){
                    ofertas.add(o);
                }
            }
        }
        return ofertas;
    }

    /**
     * Acepta la oferta indicada y la pone como DISPONIBLE
     * @param oferta Oferta a aceptar
     */
    public void aceptarOferta(Oferta oferta) {
        oferta.setEstado(Estado.DISPONIBLE);
        this.gui.aceptarOferta("La oferta se ha aceptado");
    }

    /**
     * Rechaza la oferta indicada, anade las modificaciones propuestas al dueno y la pone como RECHAZADA
     * @param oferta Oferta a rechazar
     */
    public void rechazarOferta(Oferta oferta, String modificaciones) {
        try {
            oferta.getInmueble().getDueno().anadirModificaciones(modificaciones, true);
            oferta.setEstado(Estado.RECHAZADO);
            this.gui.rechazarOferta("La oferta se ha rechazado");
        }catch (Exception e){
            this.gui.rechazarOferta(e.getMessage());
        }
    }

    /**
     * Llama al GUI para ir a la pantalla de usuarios bloqueados
     */
    public void goUsuariosBloqueados() {
        this.gui.goUsuariosBloqueados();
    }

    /**
     * LLama al GUI para volver a la pantalla de usuarios bloqueados
     */
    public void volverUsuariosBloqueados(){
        this.gui.volverUsuariosBloqueados();
    }

    /**
     * Llama al GUI para ir a la pantalla de alquilar
     * @param oferta Oferta que se quiere alquilar
     */
    public void goAlquilar(Oferta oferta){
        this.oferta=oferta;
        this.gui.goAlquilar();
    }

    public void goAlquilarR(Oferta oferta){
        this.flag=1;
        this.oferta=oferta;
        this.gui.goAlquilar();
    }

    public void volverComentario(){
        this.gui.volverComentario();
    }

    /**
     * Devuelve el comentario
     * @return Comentario seleccionado
     */
    public Comentario getComentario(){return comentario;}

    /**
     * Devuelve la valoracion media de la oferta seleccionada
     * @return Cadena con la valoracion medio
     */
    public String getValoracion(){
        double x=0;
        int i=0;
        for(Opinion o :oferta.getOpiniones()){
            if(o instanceof Valoracion){
                x+=((Valoracion) o).getPuntuacion();
                i++;
            }
        }
        if(x==0){
            return String.valueOf(x);
        }
        x=x/i;
        return String.valueOf(x);
    }

    /**
     * Anade un comentario al oferta seleccionada y llama al GUI
     * @param texto Cadena con el comentario a andir
     */
    public void publicarComentario(String texto){
        try{
            if(oferta.anadirOpinion(new Comentario((Demandante)usr,texto))){
                this.gui.publicarOK("Comentario publicado");
            } else {
                this.gui.publicarOK("Error al publicar");
            }
        }catch (Exception e){
            this.gui.publicarOK(e.getMessage());
        }
    }

    /**
     * Anade un comentario al oferta seleccionada y llama al GUI
     * @param comentario Comentario a anadir
     */
    public void publicarComentario(Comentario comentario){
        try{
            if(this.comentario.anadirComentario(comentario)){
                this.gui.anadirCOmentarioOK("Comentario publicado correctamente");
            } else {
                this.gui.anadirCOmentarioOK("Error al publicar el comentario");
            }
        } catch (Exception e){
            this.gui.anadirCOmentarioOK(e.getMessage());
        }
    }

    /**
     * Llama al GUI para ir a la pantalla de comrpobar reserva
     */
    public void goComprobarReserva(){
        this.gui.goComprobarReserva();
    }

    /**
     * Devuelve una lista con las ofertas rechazadas del ofertante logueado y llama al GUI
     * @return Lista con las ofertas rechazadas
     */
    public List<Oferta> getOfertasRechazadas() {
        List<Oferta> ofertas = new ArrayList<>();

        for(Inmueble i: ((Ofertante)usr).getInmuebles()){
            for(Oferta o: i.getOfertas()){
                if(o.getEstado()==Estado.RECHAZADO){
                    ofertas.add(o);
                }
            }
        }

        return ofertas;
    }

    /**
     * LLama al GUI para ir a la pantalla de ver modificaciones propuestas
     */
    public void goVerModificaciones() {
        this.gui.goVerModificaciones();
    }

    /**
     * LLama al GUI para ir a la pantalla de modificar oferta
     * @param oferta oferta a modificar
     */
    public void goModificarOferta(Oferta oferta) {
        this.oferta=oferta;
        this.gui.goModifcarOferta(oferta);
    }

    /**
     * Modifica la oferta indicada y llama al GUI
     *
     * @param precio Nuevo precio
     * @param fIni Nueva fecha de inicio
     * @param fFin Nueva fecha final
     * @param vacacional Nuevo boolean, si es vacacional o no
     * @param fianza Nueva fianza
     * @param oferta Oferta a modificar
     */
    public void modificarOferta(double precio, LocalDate fIni, LocalDate fFin, boolean vacacional
            , double fianza, Oferta oferta) {
        try {
            oferta.setPrecio(precio);
            oferta.setFechaInicio(fIni);
            oferta.setFechaFin(fFin);
            oferta.setVacacional(vacacional);
            oferta.setFianza(fianza);
            oferta.setEstado(Estado.PENDIENTE);
            this.gui.modificada("La oferta se ha modificado correctamente");
        }catch (Exception e){
            this.gui.modificada(e.getMessage());
        }
    }

    /**
     * Desbloquea al demandante indicado y llama la GUI
     * @param demandante Demandante a desbloquear
     */
    public void desbloquearUsuario(Demandante demandante) {
        if(demandante.desbloquear()){
            this.gui.desbloqueado("El usuaario ha sido desbloqueado");
        }else{
            this.gui.desbloqueado("Error al desbloquear");
        }
    }

    /**
     * Desbloquea al demandante, modifica su tarjeta y llama al GUI
     * @param demandante Demandante a desbloquear
     * @param tarjeta Nuevo numero de tarjeta
     */
    public void desbloquearUsuario(Demandante demandante, String tarjeta) {
        try {
            if (demandante.desbloquear()) {
                demandante.setTarjeta(tarjeta);
                this.gui.desbloqueado("El usuaario ha sido desbloqueado y la tarjeta se ha modificado");
            } else {
                this.gui.desbloqueado("Error al desbloquear");
            }
        }catch (Exception e){
            demandante.bloquear();
            this.gui.desbloqueado(e.getMessage());
        }
    }

    /**
     * Comprueba la validez de las reservas de los demandantes
     */
    public void comprobarReservas() {
        muzska.comprobarReservas();
    }

    /**
     * Reserva una oferta
     * @param oferta, oferta a reservar
     */
    public void reservar(Oferta oferta){

        try {
            if (oferta.reservar((Demandante) usr)) {
                this.gui.reservaOK("Reserva realizada correctamente");
            } else {
                this.gui.reservaOK("Error al reservar");
            }
        } catch (Exception e){
            this.gui.reservaOK(e.getMessage());
        }

    }

    /**
     * Llama al GUI para ir a la pantalla de reservar
     * @param oferta Oferta que se quiere reservar
     */
    public void goReserva(Oferta oferta){
        this.oferta=oferta;
        this.gui.goReserva();
    }

    /**
     * Obtiene el demandante guardado
     * @return Demandante, ultimo demandante guardado en el controlador
     */
    public Demandante getDemandante() {
        return demandante;
    }

    /**
     * Obtiene el flag del controlador
     * @return int, flag del controlador
     */
    public int getFlag(){
        return flag;
    }

    /**
     * Llama al GUI para ir a la pantalla de comprobar reserva
     */
    public void volverCReserva(){

        this.gui.volverCReserva();
    }
}