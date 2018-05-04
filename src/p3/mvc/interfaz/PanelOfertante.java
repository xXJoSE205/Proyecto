package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOfertante extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("OFERTANTE");
    private JLabel etiqueta2;
    private JLabel texto = new JLabel("Error al cerrar sesion");
    private GuiInmobiliaria gui;
    private JPanel select = new JPanel(new GridLayout(4, 1));
    private ButtonGroup grupo = new ButtonGroup();

    JButton volver = new JButton("Desconectarse");
    JButton crearInm = new JButton("Crear Inmueble");
    JButton verInmuebles = new JButton("Ver Inmuebles");
    JButton modificaciones = new JButton("Ver modificaciones propuestas");

    PanelOfertante(GuiInmobiliaria gui) {
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        etiqueta2 = new JLabel("Bienvenido " + gui.getControlador().getCliente().getNombre());
        grupo.add(volver);
        grupo.add(crearInm);
        grupo.add(verInmuebles);
        grupo.add(modificaciones);
        select.add(volver);
        select.add(crearInm);
        select.add(verInmuebles);
        select.add(modificaciones);
        select.setVisible(true);
        texto.setVisible(false);

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, select, 5, SpringLayout.WEST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, select, 20, SpringLayout.NORTH, etiqueta2);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, select);
        this.add(etiqueta1);
        this.add(select);
        this.add(etiqueta2);
        this.add(texto);
        this.setVisible(true);

        volver.addActionListener(this);
        crearInm.addActionListener(this);
        verInmuebles.addActionListener(this);
        modificaciones.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {
        texto.setVisible(false);
        if (evento.getSource() == volver) {
            gui.getControlador().logout();
        } else if (evento.getSource() == crearInm) {
            gui.getControlador().goCrearInmueble();
        } else if (evento.getSource() == verInmuebles) {
            gui.getControlador().goVerInmuebles();
        } else if (evento.getSource() == modificaciones) {
            gui.getControlador().goVerModificaciones();
        }
    }

    public void setError(String error) {
        texto.setVisible(true);
        texto.setForeground(java.awt.Color.red);
    }

    public void creadaOK(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
