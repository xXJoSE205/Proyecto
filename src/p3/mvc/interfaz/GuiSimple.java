package p3.mvc.interfaz;
import p3.mvc.controlador.Controlador;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class GuiSimple extends JFrame /*implements ChangeListener */{
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
        panelLogin = new PanelLogin();

        // a�adir componentes al contenedor
        contenedor.add(panelLogin);
        // this.pack();

        // visibilidad inicial
        panelLogin.setVisible( true );

        // Propuesta: PERMITIR REGRESAR A PANEL LOGIN DESDE CUALQUIER PESTA�A
        // Proposed work: ALLOW RETURN TO PANEL LOGIN FROM ANY TAB

        // Para realizar acciones al cambiar de pesta�as

        // mostrar this, en otros ejemplos era ventana, ahora this
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


    /*@Override
    public void stateChanged(ChangeEvent ev) {
        // solamente a efectos de seguimiento del programa
        System.out.println( pesta�as.getSelectedIndex() );
        System.out.println( pesta�as.getSelectedComponent() );
        this.panelPares.limpiaCampo();
        this.panelPalindromos.limpiaCampo();
    }

    public void loginResult(boolean loginOK) {
        if (loginOK) {
            panelLogin.setVisible( false );
            pesta�as.setVisible( true );
        } else {
            this.panelLogin.setError("login incorrecto");
        }
    }*/
}

