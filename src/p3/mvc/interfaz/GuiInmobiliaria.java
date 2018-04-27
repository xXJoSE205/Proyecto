package p3.mvc.interfaz;

import p3.mvc.controlador.Controlador;
import p3.mvc.modelo.Sistema;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
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
    private Controlador controlador;
    private PanelDemandante panelDemandante;

    public GuiInmobiliaria(String titulo) {
        super(titulo);

        // obtener contenedor, asignar layout
        Container contenedor = this.getContentPane(); // antes: ventana.getContentPane();
        contenedor.setLayout(new FlowLayout());

        // crear componentes
        panelPrincipal = new PanelPrincipal(this);
        panelLogin = new PanelLogin(this);
        panelBusqueda = new PanelBusqueda(this);
        panelBAvanzada = new PanelBusquedaAvanzada();
        panelCOferta = new PanelCrearOferta();
        panelCInmueble = new PanelCrearInmueble();
        panelComentario = new PanelComentario(3);
        panelDemandante = new PanelDemandante(this,"Jorge");

        // a�adir componentes al contenedor
        contenedor.add(panelPrincipal);
        contenedor.add(panelLogin);
        /*contenedor.add(panelBusqueda);
        contenedor.add(panelBAvanzada);
        contenedor.add(panelCOferta);
        contenedor.add(panelCInmueble);
        contenedor.add(panelComentario);*/
        // this.pack();
        contenedor.add(panelDemandante);

        // visibilidad inicial
        panelPrincipal.setVisible(true);
        panelLogin.setVisible(false);
        /*panelBusqueda.setVisible(false);
        panelBAvanzada.setVisible(false);
        panelCOferta.setVisible(false);
        panelCInmueble.setVisible(false);
        panelComentario.setVisible(false);*/
        panelDemandante.setVisible(false);

        // Propuesta: PERMITIR REGRESAR A PANEL LOGIN DESDE CUALQUIER PESTA�A
        // Proposed work: ALLOW RETURN TO PANEL LOGIN FROM ANY TAB

        // Para realizar acciones al cambiar de pesta�as

        // mostrar this, en otros ejemplos era ventana, ahora this
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,400); // remove and uncomment this.pack above
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
            //
        }else {
            this.panelLogin.setError("Datos incorrecto o tipo no elegido");
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
        if(logoutOK){
            /*panelDemandante.setVisible(false);
            panelOfertante.setVisible(false);
            panelGerente.setVisible(false);*/
            panelPrincipal.setVisible(true);
        }/*else{
            this.panelDemandante.setError("Error al cerrar sesion");
            this.panelOfertante.setError("Error al cerrar sesion");
            this.panelGerente.setError("Error al cerrar sesion");
        }*/
    }

    public void busquedaResult(List<Inmueble> resultado){
        if(resultado!=null){

        }
    }

    public void goBuscar(){

    }
}

