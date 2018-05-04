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

public class GuiInmobiliaria extends JFrame implements WindowListener{
    private PanelPrincipal panelPrincipal;
    private PanelLogin panelLogin;
    private PanelBusqueda panelBusqueda;
    private PanelBusquedaAvanzada panelBAvanzada;
    private PanelCrearOferta panelCOferta;
    private PanelCrearInmueble panelCInmueble;
    private PanelComentario panelComentario;
    private PanelDemandante panelDemandante;
    private PanelCReserva panelCReserva;
    private PanelOfertante panelOfertante;
    private PanelVerInmuebles panelVerInmuebles;
    private PanelCrearComentario panelCrearComentario;
    private AvanzadaResultado avanzadaResultado;
    private PanelBusquedaAvanzada panelBusquedaAvanzada;
    private PanelGerente panelGerente;
    private PanelComprobarOfertas panelComprobarOfertas;
    private PanelDesbloquearUsuario panelDesbloquearUsuario;
    private PanelUsuariosBloqueados panelUsuariosBloqueados;
    private PanelAlquilar panelAlquilar;
    private PanelAnadirComentario panelAnadirComentario;
    private PanelModificarOferta panelModificarOferta;
    private PanelVerModificaciones panelVerModificaciones;

    private Controlador controlador;
    private Container contenedor;
    private BusquedaResultado busquedaResultado;


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

        // anadir componentes al contenedor
        contenedor.add(panelPrincipal);
        contenedor.add(panelLogin);
        contenedor.add(panelBusqueda);
        contenedor.add(panelBAvanzada);
        contenedor.add(panelCOferta);
        contenedor.add(panelCInmueble);

        // visibilidad inicial
        panelPrincipal.setVisible(true);
        panelLogin.setVisible(false);
        panelBusqueda.setVisible(false);
        panelBAvanzada.setVisible(false);
        panelCOferta.setVisible(false);
        panelCInmueble.setVisible(false);

        // Propuesta: PERMITIR REGRESAR A PANEL LOGIN DESDE CUALQUIER PESTANA
        // Proposed work: ALLOW RETURN TO PANEL LOGIN FROM ANY TAB

        // Para realizar acciones al cambiar de pestanas

        // mostrar this, en otros ejemplos era ventana, ahora this
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,1000); // remove and uncomment this.pack above
        this.setVisible(true);
        this.setResizable(true);

        this.addWindowListener(this);
    }

    public void setControlador(Controlador c) {
        this.controlador = c;
    }

    public Controlador getControlador() {
        return this.controlador;
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        //intercept the window close event so that data can be saved to disk at this point
        System.out.println("Guardando datos...");
        this.getControlador().logout();
        this.getControlador().saveData();
        dispose();  //dispose the frame
        System.exit(0);
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

    public void volverLogin() {
        panelLogin.setVisible(false);
        panelPrincipal.setVisible(true);
    }

    public void goLogin() {
        panelPrincipal.setVisible(false);
        panelLogin.setVisible(true);
    }

    public void logout(boolean logoutOK) {
        if(logoutOK) {
            if(panelDemandante!=null) {
                panelDemandante.setVisible(false);
            } else if(panelOfertante!=null) {
                panelOfertante.setVisible(false);
            } else if(panelGerente!=null) {
                panelGerente.setVisible(false);
            }
            this.getControlador().quitarLogin();
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

    public void goAvanzada(){
        panelDemandante.setVisible(false);
        panelBAvanzada.setVisible(true);
    }

    public void volverDemandante(){
        panelCReserva.setVisible(false);
        panelBusqueda.setVisible(false);
        panelBusquedaAvanzada.setVisible(false);
        panelDemandante.setVisible(true);
    }

    public void goBusquedaResultado(){
        panelBusqueda.setVisible(false);
        busquedaResultado = new BusquedaResultado(this);
        contenedor.add(busquedaResultado);
        busquedaResultado.setVisible(true);

    }

    public void volverDeBusqueda(Cliente usr) {
        panelBusqueda.setVisible(false);
        if(usr==null){
            panelPrincipal.setVisible(true);
        }else if(usr instanceof Demandante){
            panelDemandante.setVisible(true);
        }else if(usr instanceof Ofertante){
            panelOfertante.setVisible(true);
        }
    }

    public void volverBusqueda(){
       busquedaResultado.setVisible(false);
       panelBusqueda.setVisible(true);
    }

    public void goBusqueda() {
        if(panelDemandante!=null)
            panelDemandante.setVisible(false);
        panelPrincipal.setVisible(false);
        panelBusqueda.setVisible(true);
    }

    public void volverOferta(){
        panelComentario.setVisible(false);
        panelBusquedaAvanzada.setVisible(true);
    }

    public void goCrearInmueble() {
        panelOfertante.setVisible(false);
        panelCInmueble.setVisible(true);
    }

    public void goCrearOferta(Inmueble inmueble) {
        panelVerInmuebles.setVisible(false);
        panelCOferta.setInmueble(inmueble);
        panelCOferta.setVisible(true);
    }

    public void volverOfertante(){
        panelCInmueble.setVisible(false);
        if(panelVerInmuebles!=null)
            panelVerInmuebles.setVisible(false);
        if(panelVerModificaciones!=null)
            panelVerModificaciones.setVisible(false);
        panelOfertante.setVisible(true);
    }

    public void volverVerInmuebles(){
        panelCOferta.setVisible(false);
        panelVerInmuebles.setVisible(true);
    }

    public void creadoOK(String texto) {
        panelCInmueble.creadoOK(texto);
    }

    public void goVerInmuebles() {
        panelOfertante.setVisible(false);
        panelVerInmuebles = new PanelVerInmuebles(this);
        contenedor.add(panelVerInmuebles);
        panelVerInmuebles.setVisible(true);
    }

    public void creadaOK(String texto) {
        panelCOferta.creadaOK(texto);
    }

    public void goDesbloquearUsuarios(Demandante demandante) {
        panelGerente.setVisible(false);
        panelDesbloquearUsuario.setVisible(true);
    }

    public void volverGerente(){
        panelComprobarOfertas.setVisible(false);
        panelUsuariosBloqueados.setVisible(false);
        panelGerente.setVisible(true);
    }

    public void goComprobarOfertas() {
        panelGerente.setVisible(false);
        panelComprobarOfertas.setVisible(true);
    }

    public void aceptarOferta(String texto) {
        panelDesbloquearUsuario.setError(texto);
    }

    public void rechazarOferta(String texto) {
        panelDesbloquearUsuario.setError(texto);
    }

    public void goUsuariosBloqueados() {
        panelGerente.setVisible(false);
        panelUsuariosBloqueados.setVisible(true);
    }

    public void volverUsuariosBloqueados() {
        panelDesbloquearUsuario.setVisible(false);
        panelUsuariosBloqueados.setVisible(true);
    }

    public void volverRAvanzada(){
        panelCrearComentario.setVisible(false);
        panelAlquilar.setVisible(false);
        panelComentario.setVisible(false);
        avanzadaResultado.setVisible(true);
    }

    public void volverBAvanzada(){
        avanzadaResultado.setVisible(false);
        panelBusquedaAvanzada.setVisible(true);
    }

    public void alquilerOK(String texto){
        panelAlquilar.creadaOK(texto);
    }

    public void goAlquilar(){
        avanzadaResultado.setVisible(false);
        panelAlquilar = new PanelAlquilar(this);
        contenedor.add(panelAlquilar);
        panelAlquilar.setVisible(true);
    }

    public void goAnadirComentario(){
        panelComentario.setVisible(false);
        panelAnadirComentario.setVisible(true);

    }

    public void goCrearComentario(){
        panelComentario.setVisible(false);
        panelAnadirComentario.setVisible(true);
    }

    public void volverComentario(){
        panelAnadirComentario.setVisible(false);
        panelComentario.setVisible(true);
    }

    public void comentarioOK(String texto){
        panelAnadirComentario.creadaOK(texto);
    }

    public void errorBusqueda(String texto){
        panelBusqueda.creadaOK(texto);
    }

    public void avanzadaError(String texto){
        panelBusquedaAvanzada.creadaOK(texto);
    }

    public void goRAvanzada(){
        panelBusquedaAvanzada.setVisible(false);
        avanzadaResultado = new AvanzadaResultado(this);
        contenedor.add(avanzadaResultado);
        avanzadaResultado.setVisible(true);
    }

    public void valoracionOK(String texto){
        panelComentario.creadaOK(texto);
    }

    public void publicarOK(String texto){
        panelCrearComentario.creadaOK(texto);
    }

    public void cancelarReservaOK(String texto){
        panelCReserva.creadaOK(texto);
    }

    public void goComprobarReserva(){
        panelDemandante.setVisible(false);
        panelCReserva = new PanelCReserva(this);
        contenedor.add(panelCReserva);
        panelCReserva.setVisible(true);
    }

    public void goVerModificaciones() {
        panelOfertante.setVisible(false);
        panelVerModificaciones = new PanelVerModificaciones(this);
        contenedor.add(panelVerModificaciones);
        panelVerModificaciones.setVisible(true);
    }

    public void goModifcarOferta(Oferta oferta) {
        panelVerInmuebles.setVisible(false);
        panelModificarOferta.setOferta(oferta);
        panelModificarOferta.setVisible(true);
    }

    public void modificada(String texto) {
        panelModificarOferta.setError(texto);
    }

    public void desbloqueado(String texto) {
        panelDesbloquearUsuario.setError(texto);
    }

    public void anadirCOmentarioOK(String texto){
        panelAnadirComentario.creadaOK(texto);
    }

    public void volverPrincipal() {
        panelBusqueda.setVisible(false);
        panelPrincipal.setVisible(true);
    }
}

