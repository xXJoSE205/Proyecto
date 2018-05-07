package p3.mvc.interfaz;

import p3.mvc.controlador.Controlador;
import p3.mvc.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Esta clase contiene una ventana que controla paneles
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
public class GuiInmobiliaria extends JFrame implements WindowListener{
    /**Panel princioal*/
    private final PanelPrincipal panelPrincipal;
    /**Panel login*/
    private final PanelLogin panelLogin;
    /**Panel de busqueda*/
    private final PanelBusqueda panelBusqueda;
    /**Panel de busqueda avanzada*/
    private final PanelBusquedaAvanzada panelBAvanzada;
    /**Panel crear oferta*/
    private final PanelCrearOferta panelCOferta;
    /**Panel crear inmueble*/
    private final PanelCrearInmueble panelCInmueble;
    /**Panel de los comentarios*/
    private PanelComentario panelComentario;
    /**Panel de demandante*/
    private PanelDemandante panelDemandante;
    /**Panel comprobar reserva*/
    private PanelCReserva panelCReserva;
    /**Panel de ofertante*/
    private PanelOfertante panelOfertante;
    /**Panel ver inmuebles*/
    private PanelVerInmuebles panelVerInmuebles;
    /**Panel crear comentarios*/
    private PanelCrearComentario panelCrearComentario;
    /**Panel con el resultado de busqueda avanzada*/
    private AvanzadaResultado avanzadaResultado;
    /**Panel del gerente*/
    private PanelGerente panelGerente;
    /**Panel para comprobar las ofertas*/
    private PanelComprobarOfertas panelComprobarOfertas;
    /**Pane desbloquear usuarios*/
    private PanelDesbloquearUsuario panelDesbloquearUsuario;
    /**Panel ver usuarios bloqueados*/
    private PanelUsuariosBloqueados panelUsuariosBloqueados;
    /**Panel alquilar*/
    private PanelAlquilar panelAlquilar;
    /**Panel anadir comentario*/
    private final PanelAnadirComentario panelAnadirComentario;
    /**Panel modificar oferta*/
    private PanelModificarOferta panelModificarOferta;
    /**Panel ver modificaciones*/
    private PanelVerModificaciones panelVerModificaciones;
    /**Panel reservar*/
    private PanelReservar panelReservar;
    /**Controlador de la aplicacion*/
    private Controlador controlador;
    /**Contenedor para almacenar los paneles*/
    private final Container contenedor;
    /**Panel con el resultado de la busqueda*/
    private BusquedaResultado busquedaResultado;


    /**
     * Constructor de GuiInmobiliaria
     *
     * @param titulo Nombre de la aplicacion
     */
    public GuiInmobiliaria(String titulo) {
        super(titulo);

        Image icon = new ImageIcon("definitivo3.png").getImage();
        setIconImage(icon);

        // obtener contenedor, asignar layout
        contenedor = this.getContentPane(); // antes: ventana.getContentPane();
        contenedor.setLayout(new FlowLayout());

        // crear componentes
        panelPrincipal = new PanelPrincipal(this);
        panelLogin = new PanelLogin(this);
        panelBusqueda = new PanelBusqueda(this);
        panelBAvanzada = new PanelBusquedaAvanzada(this);
        panelCOferta = new PanelCrearOferta(this);
        panelCInmueble = new PanelCrearInmueble(this);
        panelAnadirComentario = new PanelAnadirComentario(this);
        panelCrearComentario = new PanelCrearComentario(this);

        // anadir componentes al contenedor
        contenedor.add(panelPrincipal);
        contenedor.add(panelLogin);
        contenedor.add(panelBusqueda);
        contenedor.add(panelBAvanzada);
        contenedor.add(panelCOferta);
        contenedor.add(panelCInmueble);
        contenedor.add(panelCrearComentario);
        contenedor.add(panelAnadirComentario);

        // visibilidad inicial
        panelPrincipal.setVisible(true);
        panelLogin.setVisible(false);
        panelBusqueda.setVisible(false);
        panelBAvanzada.setVisible(false);
        panelCOferta.setVisible(false);
        panelCInmueble.setVisible(false);
        panelCrearComentario.setVisible(false);
        panelAnadirComentario.setVisible(false);

        // Propuesta: PERMITIR REGRESAR A PANEL LOGIN DESDE CUALQUIER PESTANA
        // Proposed work: ALLOW RETURN TO PANEL LOGIN FROM ANY TAB

        // Para realizar acciones al cambiar de pestanas

        // mostrar this, en otros ejemplos era ventana, ahora this
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,700); // remove and uncomment this.pack above
        this.setVisible(true);
        this.setResizable(true);

        this.addWindowListener(this);
    }

    /**
     * Añade el controlador al GUI
     * @param c controlador que se quiere añadir
     */
    public void setControlador(Controlador c) {
        this.controlador = c;
    }

    /**
     * Obtiene el controlador del GUI
     * @return Controlador, el controlador del GUI
     */
    public Controlador getControlador() {
        return this.controlador;
    }

    /**
     * Accion a realizar cuando se abre la aplicacion
     * @param e evento
     */
    @Override
    public void windowOpened(WindowEvent e) {}

    /**
     * Accion a realizar cuando se cierra la aplicacion
     * @param e evento
     */
    @Override
    public void windowClosing(WindowEvent e) {
        int res = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres salir de Muzska?"
                , "¿Salir?", JOptionPane.YES_NO_OPTION);
        if(res==JOptionPane.YES_OPTION) {
            ((GuiInmobiliaria)e.getSource()).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //intercept the window close event so that data can be saved to disk at this point
            System.out.println("Guardando datos...");
            this.getControlador().logout();
            this.getControlador().comprobarReservas();
            this.getControlador().saveData();
            dispose();  //dispose the frame
            System.exit(0);
        }else{
            ((GuiInmobiliaria)e.getSource()).setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

    /**
     * Guarda los datos del Sistema en el fichero "muzska.ser"
     *
     * @param sistema Sistema del cual se quieren guardar los datos
     */
    public void guardarDatos(Sistema sistema){
        try {
            FileOutputStream fileOut = new FileOutputStream("muzska.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sistema);
            out.close();
            fileOut.close();
            System.out.println("Datos serializables guardados en \"muzska.ser\"");
        }catch(IOException ioe) {
            System.out.println("Error al manejar el fichero \"muzska.ser\"");
            ioe.printStackTrace();
        }
    }

    /**
     * Cambia el panel cuando se realiza el login
     * @param loginOK Boolean, si se ha realizado correctamente el login
     */
    public void loginResult(boolean loginOK) {
        if (loginOK) {
            panelLogin.setVisible(false);
            if(controlador.getCliente() instanceof Ofertante) {
                panelOfertante = new PanelOfertante(this);
                contenedor.add(panelOfertante);
                panelOfertante.setVisible(true);
            }else if(controlador.getCliente() instanceof Demandante) {
                panelDemandante = new PanelDemandante(this);
                contenedor.add(panelDemandante);
                panelDemandante.setVisible(true);
            }else{
                panelGerente = new PanelGerente(this);
                contenedor.add(panelGerente);
                panelGerente.setVisible(true);
            }
        }else {
            panelLogin.setError("Datos incorrecto o tipo no elegido");
        }
    }

    /**
     * Vuelve del panel de login al principal
     */
    public void volverLogin() {
        panelLogin.setVisible(false);
        this.getControlador().comprobarReservas();
        panelPrincipal.setVisible(true);
    }

    /**
     * Va del panel principal al de login
     */
    public void goLogin() {
        panelPrincipal.setVisible(false);
        panelLogin.setVisible(true);
        panelLogin.text();
    }

    /**
     * Cambia del panel del demandante, ofertante o gerente al principal
     * @param logoutOK Boolean, si se ha realizado correctamente el logout o no
     */
    public void logout(boolean logoutOK) {
        if(logoutOK) {
            if(panelDemandante!=null)
                panelDemandante.setVisible(false);
            if(panelOfertante!=null)
                panelOfertante.setVisible(false);
            if(panelGerente!=null)
                panelGerente.setVisible(false);
            this.getControlador().comprobarReservas();
            panelPrincipal.setVisible(true);
        }else{
            if(panelDemandante!=null)
                this.panelDemandante.setError("Error al cerrar sesion");
            if(panelOfertante!=null)
                this.panelOfertante.setError("Error al cerrar sesion");
            if(panelGerente!=null)
                this.panelGerente.setError("Error al cerrar sesion");
        }
    }

    /**
     * Cambia el panel del demandante al de busqueda avanzada
     */
    public void goAvanzada(){
        panelDemandante.setVisible(false);
        panelBAvanzada.setVisible(true);
    }

    /**
     * Cambia el panel de comprobar reserva, el de busqueda o el de busqueda avanzada por el de demandante
     */
    public void volverDemandante(){
        if(panelCReserva!=null)
            panelCReserva.setVisible(false);
        panelBusqueda.setVisible(false);
        panelBAvanzada.setVisible(false);
        panelDemandante.setVisible(true);
    }

    /**
     * Crea un nuevo panel de resultado de busqueda y lo cambia por el de busqueda
     */
    public void goBusquedaResultado(){
        panelBusqueda.setVisible(false);
        busquedaResultado = new BusquedaResultado(this);
        contenedor.add(busquedaResultado);
        busquedaResultado.setVisible(true);

    }

    /**
     * Cambia el panel de busqueda por el principal o el demandante
     * @param usr, Cliente logeado actualmente
     */
    public void volverDeBusqueda(Cliente usr) {
        panelBusqueda.setVisible(false);
        if(usr==null){
            this.getControlador().comprobarReservas();
            panelPrincipal.setVisible(true);
        }else if(usr instanceof Demandante){
            panelDemandante.setVisible(true);
        }
    }

    /**
     * Cambia el panel del resultado de la busqueda por el de busqueda
     */
    public void volverBusqueda(){
       busquedaResultado.setVisible(false);
       contenedor.remove(busquedaResultado);
       panelBusqueda.setVisible(true);
    }

    /**
     * Cambia el panel de demandante o principal por el de busqueda
     */
    public void goBusqueda() {
        if(panelDemandante!=null)
            panelDemandante.setVisible(false);
        panelPrincipal.setVisible(false);
        panelBusqueda.setVisible(true);
    }

    /**
     * Cambia el panel de ofertante por el de crear inmueble
     */
    public void goCrearInmueble() {
        panelOfertante.setVisible(false);
        panelCInmueble.setVisible(true);
    }

    /**
     * Cambnia el panel de ver inmuebles por el de crear oferta
     * @param inmueble
     */
    public void goCrearOferta(Inmueble inmueble) {
        panelVerInmuebles.setVisible(false);
        panelCOferta.setInmueble(inmueble);
        panelCOferta.setVisible(true);
    }

    /**
     * Cambia el panel de ver inmuebles, ver modificaciones, modificar oferta o crear inmueble por el de ofertante
     */
    public void volverOfertante(){
        if(panelVerInmuebles!=null)
            panelVerInmuebles.setVisible(false);
        if(panelVerModificaciones!=null)
            panelVerModificaciones.setVisible(false);
        if(panelModificarOferta!=null){
            panelModificarOferta.setVisible(false);
        }
        if(panelCInmueble!=null)
            panelCInmueble.setVisible(false);
        panelOfertante.setVisible(true);
    }

    /**
     * Cambia el panel de crear oferta por el de ver inmuebles
     */
    public void volverVerInmuebles(){
        panelCOferta.setVisible(false);
        panelVerInmuebles.setVisible(true);
    }

    /**
     * Cambia el mensaje del panel crear inmueble
     * @param texto texto que se quiere anadir al mensaje
     */
    public void creadoOK(String texto) {
        panelCInmueble.creadoOK(texto);
    }

    /**
     * Crea un nuevo panel de ver inmueble y lo cambia por el de ofertante
     */
    public void goVerInmuebles() {
        panelOfertante.setVisible(false);
        panelVerInmuebles = new PanelVerInmuebles(this);
        contenedor.add(panelVerInmuebles);
        panelVerInmuebles.setVisible(true);
    }

    /**
     * Cambia el mensaje de crear oferta
     * @param texto texto que se quiere anadir al mensaje
     */
    public void creadaOK(String texto) {
        panelCOferta.creadaOK(texto);
    }

    /**
     * Crea un nuevo panel de desbloquear usuarios y lo cambia por el de usuarios bloqueados
     * @param demandante demandante sobre el que se quiere crear el panel de desbloquear usuario
     */
    public void goDesbloquearUsuarios(Demandante demandante) {
        panelUsuariosBloqueados.setVisible(false);
        panelDesbloquearUsuario = new PanelDesbloquearUsuario(this);
        panelDesbloquearUsuario.setDemandante(demandante);
        contenedor.add(panelDesbloquearUsuario);
        panelDesbloquearUsuario.setVisible(true);
    }

    /**
     * Cambia el panel de comprobar ofertas o el de usuarios bloqueados por el de gerente
     */
    public void volverGerente(){
        if(panelComprobarOfertas!=null)
            panelComprobarOfertas.setVisible(false);
        if(panelUsuariosBloqueados!=null)
            panelUsuariosBloqueados.setVisible(false);
        panelGerente.setVisible(true);
    }

    /**
     * Crea un nuevo panel de comprobar ofertas y lo cambia por el de gerente
     */
    public void goComprobarOfertas() {
        panelGerente.setVisible(false);
        panelComprobarOfertas = new PanelComprobarOfertas(this);
        contenedor.add(panelComprobarOfertas);
        panelComprobarOfertas.setVisible(true);
    }

    /**
     * Cambia el mensaje comproba ofertas
     * @param texto texto que se quiere anadir al mensaje
     */
    public void aceptarOferta(String texto) {
        panelComprobarOfertas.setError(texto);
    }

    /**
     * Cambia el mensaje comproba ofertas
     * @param texto texto que se quiere anadir al mensaje
     */
    public void rechazarOferta(String texto) {
        panelComprobarOfertas.setError(texto);
    }

    /**
     * Crea un nuevo panel usuario bloqueado y lo cambia por el de gerente
     */
    public void goUsuariosBloqueados() {
        panelGerente.setVisible(false);
        panelUsuariosBloqueados = new PanelUsuariosBloqueados(this);
        contenedor.add(panelUsuariosBloqueados);
        panelUsuariosBloqueados.setVisible(true);
    }

    /**
     * Cambia el panel de desbloquear usuarios, crear comentario, reservar o alquilar por el de usuarios bloqueados
     */
    public void volverUsuariosBloqueados() {
        if(panelDesbloquearUsuario!=null)
            panelDesbloquearUsuario.setVisible(false);
        panelUsuariosBloqueados.setVisible(true);
    }

    /**
     * Cambia el panel de crear comentario por el resultado de la busqueda avanzada
     */
    public void volverRAvanzada(){
        panelCrearComentario.setVisible(false);
        avanzadaResultado.setVisible(true);
    }

    /**
     * Cambia el panel de reservar por el resultado de la busqueda avanzada
     */
    public void volverRAvanzada2(){
        panelReservar.setVisible(false);
        avanzadaResultado.setVisible(true);
    }

    /**
     * Cambia el panel de los cometarios por el resultado de la busqueda avanzada
     */
    public void volverRAvanzada3(){
        panelComentario.setVisible(false);
        avanzadaResultado.setVisible(true);
    }

    /**
     * Cambia el panel de alquilar por el resultado de la busqueda avanzada
     */
    public void volverRAvanzada4(){
        panelAlquilar.setVisible(false);
        avanzadaResultado.setVisible(true);
    }

    /**
     * Cambia el panel del resultado de la busqueda avanzada por el de busqueda avanzada
     */
    public void volverBAvanzada(){
        avanzadaResultado.setVisible(false);
        panelBAvanzada.setVisible(true);
    }

    /**
     * Cambia el mensaje del panel de alquilar
     * @param texto texto que se quiere anadir al mensaje
     */
    public void alquilerOK(String texto){
        panelAlquilar.setError(texto);
    }

    /**
     * Cambia el panel del resultado de busqueda avanzada o el de comprobar reserva por el de alquilar
     */
    public void goAlquilar(){
        if(avanzadaResultado!=null)
            avanzadaResultado.setVisible(false);
        if(panelCReserva!=null)
            panelCReserva.setVisible(false);
        panelAlquilar = new PanelAlquilar(this);
        contenedor.add(panelAlquilar);
        panelAlquilar.setVisible(true);
    }

    /**
     * Cambia el panel de comentario por el de anadir comentario
     */
    public void goAnadirComentario(){
        panelComentario.setVisible(false);
        panelAnadirComentario.setVisible(true);

    }

    /**
     * Cambia el panel del resultado de la busqueda avanzada por el de crear comentario
     */
    public void goCrearComentario(){
        avanzadaResultado.setVisible(false);
        panelCrearComentario.setVisible(true);
    }

    /**
     * Cambia el panel de anadir comentario por el de comentario
     */
    public void volverComentario(){
        panelAnadirComentario.setVisible(false);
        panelComentario.setVisible(true);
    }

    /**
     * Cambia el mensaje del panel de busqueda
     * @param texto texto del mensaje
     */
    public void errorBusqueda(String texto){
        panelBusqueda.setError(texto);
    }

    /**
     * Cambia el mensaje de busqueda avanzada
     * @param texto texto del mensaje
     */
    public void avanzadaError(String texto){
        panelBAvanzada.setError(texto);
    }

    /**
     * Crea un nuevo panel de resultado de busqueda avanzada y lo cambia por el de busqueda avanzada
     */
    public void goRAvanzada(){
        panelBAvanzada.setVisible(false);
        avanzadaResultado = new AvanzadaResultado(this);
        contenedor.add(avanzadaResultado);
        avanzadaResultado.setVisible(true);
    }

    /**
     * Cambia el mensaje del panel de comentario
     * @param texto texto del mensaje
     */
    public void valoracionOK(String texto){
        panelComentario.setError(texto);
    }

    /**
     * Cambia el mensaje del panel crear comentario
     * @param texto texto del mensaje
     */
    public void publicarOK(String texto){
        panelCrearComentario.creadoOK(texto);
    }

    /**
     * Cambia el mensaje del panel comprobar reserva
     * @param texto texto del mensaje
     */
    public void cancelarReservaOK(String texto){
        panelCReserva.setError(texto);
    }

    /**
     * Crea un nuevo panel de comprobar reserva y lo cambia por el de demandante
     */
    public void goComprobarReserva(){
        panelDemandante.setVisible(false);
        panelCReserva = new PanelCReserva(this);
        contenedor.add(panelCReserva);
        panelCReserva.setVisible(true);
    }

    /**
     * Crea un nuevo panel para ver las modificaciones y lo cambia por el de ofertante
     */
    public void goVerModificaciones() {
        panelOfertante.setVisible(false);
        panelVerModificaciones = new PanelVerModificaciones(this);
        contenedor.add(panelVerModificaciones);
        panelVerModificaciones.setVisible(true);
    }

    /**
     * Crea un nuevo panel de modificaciones y lo cambia por el de ver modificaciones
     * @param oferta oferta que se quiere modificar
     */
    public void goModifcarOferta(Oferta oferta) {
        panelVerModificaciones.setVisible(false);
        panelModificarOferta= new PanelModificarOferta(this);
        contenedor.add(panelModificarOferta);
        panelModificarOferta.setVisible(true);
    }

    /**
     * Cambia el mensaje del panel modificar oferta
     * @param texto texto del mensaje
     */
    public void modificada(String texto) {
        panelModificarOferta.setError(texto);
    }

    /**
     * Cambia el mensaje del panel de desbloquear usuarios
     * @param texto texto del mensaje
     */
    public void desbloqueado(String texto) {
        panelDesbloquearUsuario.setError(texto);
    }

    /**
     * Cambia el mensaje del panel anadir comentario
     * @param texto texto del mensaje
     */
    public void anadirCOmentarioOK(String texto){
        panelAnadirComentario.setError(texto);
    }

    /**
     * Cambia el mensaje del panel reservar
     * @param texto texto del mensaje
     */
    public void reservaOK(String texto){
        this.panelReservar.setError(texto);
    }

    /**
     * Crea un nuevo panel de reservar y lo cambia por el de resultado de busqueda avanzada
     */
    public void goReserva(){
        avanzadaResultado.setVisible(false);
        panelReservar = new PanelReservar(this);
        contenedor.add(panelReservar);
        panelReservar.setVisible(true);

    }

    /**
     * Crea un nuevo panel de comentarios y lo cambia por el del resultado de busqueda avanzada
     */
    public void goComentarios(){
        avanzadaResultado.setVisible(false);
        panelComentario = new PanelComentario(this);
        contenedor.add(panelComentario);
        panelComentario.setVisible(true);
    }

    /**
     * Cambia el panel de alquilar por el de comprobar reserva
     */
    public void volverCReserva(){
        panelAlquilar.setVisible(false);
        panelCReserva.setVisible(true);
    }
}

