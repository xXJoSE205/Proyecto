package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase contiene la informacion del panel Ofertante
 * Crea un panel en el que se muestran las distintas acciones que puede realizar el ofertante, unos botones para
 * cerrar sesion(desconectarse), crear inmueble, ver inmuebles, y ver modificaciones propuestas
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
class PanelOfertante extends JPanel implements ActionListener {
    private final JLabel texto = new JLabel("Error al cerrar sesion");
    private final GuiInmobiliaria gui;
    private final JButton volver = new JButton("Desconectarse");
    private final JButton crearInm = new JButton("Crear Inmueble");
    private final JButton verInmuebles = new JButton("Ver Inmuebles");
    private final JButton modificaciones = new JButton("Ver modificaciones propuestas");

    /**
     * Constructor de PanelOfertante
     * @param gui GUI de la inmobiliaria
     */
    PanelOfertante(GuiInmobiliaria gui) {
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JLabel etiqueta2 = new JLabel("Bienvenido " + gui.getControlador().getCliente().getNombre());
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(crearInm);
        grupo.add(verInmuebles);
        grupo.add(modificaciones);
        JPanel select = new JPanel(new GridLayout(4, 1));
        select.add(volver);
        select.add(crearInm);
        select.add(verInmuebles);
        select.add(modificaciones);
        select.setVisible(true);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("OFERTANTE");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta2, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, select, 20, SpringLayout.NORTH, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, select);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, select);
        this.add(etiqueta1);
        this.add(select);
        this.add(etiqueta2);
        this.add(texto);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        crearInm.addActionListener(this);
        verInmuebles.addActionListener(this);
        modificaciones.addActionListener(this);
    }

    /**
     * Manejador de las acciones de los botones
     * @param evento Accion que se activa
     */
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == volver) {
            texto.setVisible(false);
            gui.getControlador().logout();
        } else if (evento.getSource() == crearInm) {
            texto.setVisible(false);
            gui.getControlador().goCrearInmueble();
        } else if (evento.getSource() == verInmuebles) {
            texto.setVisible(false);
            gui.getControlador().goVerInmuebles();
        } else if (evento.getSource() == modificaciones) {
            texto.setVisible(false);
            gui.getControlador().goVerModificaciones();
        }
    }

    /**
     * Establece el mensaje que se va a mostrar por pantalla
     * @param texto Cadena con el mensaje a mostrar
     */
    public void setError(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
