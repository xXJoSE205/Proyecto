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
    private final PanelPrincipal panelPrincipal;
    private final PanelLogin panelLogin;
    private final PanelBusqueda panelBusqueda;
    private final PanelBusquedaAvanzada panelBAvanzada;
    private final PanelCrearOferta panelCOferta;
    private final PanelCrearInmueble panelCInmueble;
    private PanelComentario panelComentario;
    private PanelDemandante panelDemandante;
    private PanelCReserva panelCReserva;
    private PanelOfertante panelOfertante;
    private PanelVerInmuebles panelVerInmuebles;
    private final PanelCrearComentario panelCrearComentario;
    private AvanzadaResultado avanzadaResultado;
    private PanelGerente panelGerente;
    private PanelComprobarOfertas panelComprobarOfertas;
    private PanelDesbloquearUsuario panelDesbloquearUsuario;
    private PanelUsuariosBloqueados panelUsuariosBloqueados;
    private PanelAlquilar panelAlquilar;
    private final PanelAnadirComentario panelAnadirComentario;
    private PanelModificarOferta panelModificarOferta;
    private PanelVerModificaciones panelVerModificaciones;
    private PanelReservar panelReservar;

    private Controlador controlador;
    private final Container contenedor;
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
        this.getControlador().comprobarReservas();
        panelPrincipal.setVisible(true);
    }

    public void goLogin() {
        panelPrincipal.setVisible(false);
        panelLogin.setVisible(true);
        panelLogin.text();
    }

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

    public void goAvanzada(){
        panelDemandante.setVisible(false);
        panelBAvanzada.setVisible(true);
    }

    public void volverDemandante(){
        if(panelCReserva!=null)
            panelCReserva.setVisible(false);
        panelBusqueda.setVisible(false);
        panelBAvanzada.setVisible(false);
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
            this.getControlador().comprobarReservas();
            panelPrincipal.setVisible(true);
        }else if(usr instanceof Demandante){
            panelDemandante.setVisible(true);
        }
    }

    public void volverBusqueda(){
       busquedaResultado.setVisible(false);
       contenedor.remove(busquedaResultado);
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
        panelBAvanzada.setVisible(true);
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
        panelUsuariosBloqueados.setVisible(false);
        panelDesbloquearUsuario = new PanelDesbloquearUsuario(this);
        panelDesbloquearUsuario.setDemandante(demandante);
        contenedor.add(panelDesbloquearUsuario);
        panelDesbloquearUsuario.setVisible(true);
    }

    public void volverGerente(){
        if(panelComprobarOfertas!=null)
            panelComprobarOfertas.setVisible(false);
        if(panelUsuariosBloqueados!=null)
            panelUsuariosBloqueados.setVisible(false);
        panelGerente.setVisible(true);
    }

    public void goComprobarOfertas() {
        panelGerente.setVisible(false);
        panelComprobarOfertas = new PanelComprobarOfertas(this);
        contenedor.add(panelComprobarOfertas);
        panelComprobarOfertas.setVisible(true);
    }

    public void aceptarOferta(String texto) {
        panelComprobarOfertas.setError(texto);
    }

    public void rechazarOferta(String texto) {
        panelComprobarOfertas.setError(texto);
    }

    public void goUsuariosBloqueados() {
        panelGerente.setVisible(false);
        panelUsuariosBloqueados = new PanelUsuariosBloqueados(this);
        contenedor.add(panelUsuariosBloqueados);
        panelUsuariosBloqueados.setVisible(true);
    }

    public void volverUsuariosBloqueados() {
        if(panelDesbloquearUsuario!=null)
            panelDesbloquearUsuario.setVisible(false);
        if(panelCrearComentario!=null)
            panelCrearComentario.setVisible(false);
        if(panelReservar!=null)
            panelReservar.setVisible(false);
        if(panelAlquilar!=null)
            panelAlquilar.setVisible(false);
        panelUsuariosBloqueados = new PanelUsuariosBloqueados(this);
        contenedor.add(panelUsuariosBloqueados);
        panelUsuariosBloqueados.setVisible(true);
    }

    public void volverRAvanzada(){
        panelCrearComentario.setVisible(false);
        avanzadaResultado.setVisible(true);
    }

    public void volverRAvanzada2(){
        panelReservar.setVisible(false);
        avanzadaResultado.setVisible(true);
    }

    public void volverRAvanzada3(){
        panelComentario.setVisible(false);
        avanzadaResultado.setVisible(true);
    }

    public void volverRAvanzada4(){
        panelAlquilar.setVisible(false);
        avanzadaResultado.setVisible(true);
    }

    public void volverBAvanzada(){
        avanzadaResultado.setVisible(false);
        panelBAvanzada.setVisible(true);
    }

    public void alquilerOK(String texto){
        panelAlquilar.setError(texto);
    }

    public void goAlquilar(){
        if(avanzadaResultado!=null)
            avanzadaResultado.setVisible(false);
        if(panelCReserva!=null)
            panelCReserva.setVisible(false);
        panelAlquilar = new PanelAlquilar(this);
        contenedor.add(panelAlquilar);
        panelAlquilar.setVisible(true);
    }

    public void goAnadirComentario(){
        panelComentario.setVisible(false);
        panelAnadirComentario.setVisible(true);

    }

    public void goCrearComentario(){
        avanzadaResultado.setVisible(false);
        panelCrearComentario.setVisible(true);
    }

    public void volverComentario(){
        panelAnadirComentario.setVisible(false);
        panelComentario.setVisible(true);
    }

    public void comentarioOK(String texto){
        panelAnadirComentario.setError(texto);
    }

    public void errorBusqueda(String texto){
        panelBusqueda.setError(texto);
    }

    public void avanzadaError(String texto){
        panelBAvanzada.setError(texto);
    }

    public void goRAvanzada(){
        panelBAvanzada.setVisible(false);
        avanzadaResultado = new AvanzadaResultado(this);
        contenedor.add(avanzadaResultado);
        avanzadaResultado.setVisible(true);
    }

    public void valoracionOK(String texto){
        panelComentario.setError(texto);
    }

    public void publicarOK(String texto){
        panelCrearComentario.creadoOK(texto);
    }

    public void cancelarReservaOK(String texto){
        panelCReserva.setError(texto);
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
        panelVerModificaciones.setVisible(false);
        //panelModificarOferta.setOferta(oferta);
        panelModificarOferta= new PanelModificarOferta(this);
        contenedor.add(panelModificarOferta);
        panelModificarOferta.setVisible(true);
    }

    public void modificada(String texto) {
        panelModificarOferta.setError(texto);
    }

    public void desbloqueado(String texto) {
        panelDesbloquearUsuario.setError(texto);
    }

    public void anadirCOmentarioOK(String texto){
        panelAnadirComentario.setError(texto);
    }

    public void goReserva(Oferta oferta) {
        panelBAvanzada.setVisible(false);
        panelReservar = new PanelReservar(this);
        contenedor.add(panelReservar);
        panelReservar.setVisible(true);
    }

    public void reservaOK(String texto){
        this.panelReservar.setError(texto);
    }

    public void goReserva(){
        avanzadaResultado.setVisible(false);
        panelReservar = new PanelReservar(this);
        contenedor.add(panelReservar);
        panelReservar.setVisible(true);

    }

    public void goComentarios(){
        avanzadaResultado.setVisible(false);
        panelComentario = new PanelComentario(this);
        contenedor.add(panelComentario);
        panelComentario.setVisible(true);
    }

    public void volverCReserva(){
        panelAlquilar.setVisible(false);
        panelCReserva.setVisible(true);
    }
}

