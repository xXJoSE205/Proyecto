package p3.mvc.interfaz;
import p3.mvc.controlador.Controlador;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class GuiSimple extends JFrame {
    private PanelPrincipal panelPrincipal;
    private PanelLogin panelLogin;
    private PanelBusqueda panelBusqueda;
    private PanelBusquedaAvanzada panelBAvanzada;
    private PanelCrearOferta panelCOferta;
    private PanelCrearInmueble panelCInmueble;
    private PanelComentario panelComentario;
    private Controlador controlador;

    public GuiSimple(String titulo) {
        super(titulo); // antes: JFrame ventana = new JFrame("Mi GUI");

        // obtener contenedor, asignar layout
        Container contenedor = this.getContentPane(); // antes: ventana.getContentPane();
        contenedor.setLayout(new FlowLayout());

        // crear componentes
        panelPrincipal = new PanelPrincipal(this);
        panelLogin = new PanelLogin(this);
        /*panelBusqueda = new PanelBusqueda();
        panelBAvanzada = new PanelBusquedaAvanzada();
        panelCOferta = new PanelCrearOferta();
        panelCInmueble = new PanelCrearInmueble();
        panelComentario = new PanelComentario(3);*/

        // a�adir componentes al contenedor
        contenedor.add(panelPrincipal);
        contenedor.add(panelLogin);
        /*contenedor.add(panelBusqueda);
        contenedor.add(panelBAvanzada);
        contenedor.add(panelCOferta);
        contenedor.add(panelCInmueble);
        contenedor.add(panelComentario);*/
        // this.pack();

        // visibilidad inicial
        panelPrincipal.setVisible(true);
        panelLogin.setVisible(false);
        /*panelBusqueda.setVisible(false);
        panelBAvanzada.setVisible(false);
        panelCOferta.setVisible(false);
        panelCInmueble.setVisible(false);
        panelComentario.setVisible(false);*/

        // Propuesta: PERMITIR REGRESAR A PANEL LOGIN DESDE CUALQUIER PESTA�A
        // Proposed work: ALLOW RETURN TO PANEL LOGIN FROM ANY TAB

        // Para realizar acciones al cambiar de pesta�as

        // mostrar this, en otros ejemplos era ventana, ahora this
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,400); // remove and uncomment this.pack above
        this.setVisible(true);
    }

    public void setControlador(Controlador c) {
        this.controlador = c;
    }

    public Controlador getControlador() {
        return this.controlador;
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
}

