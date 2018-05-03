package p3.mvc.interfaz;

import p3.mvc.modelo.Oferta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAlquilar extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("Numero de habitaciones:");
    private final JTextField numero = new JTextField("", 2);
    private JLabel etiqueta2 = new JLabel("Numero de banos:");
    private final JTextField numeroBan = new JTextField("", 2);
    private JLabel etiqueta3 = new JLabel("Dimensiones:");
    private final JTextField dim = new JTextField("", 2);
    private JLabel etiqueta4 = new JLabel("Numero de habitaciones:");
    private final JTextField planta = new JTextField("", 2);
    private JLabel etiqueta5 = new JLabel("Direccion:");
    private final JTextField direccion = new JTextField("", 50);
    private JLabel etiqueta6 = new JLabel("Precio");
    private final JTextField precio = new JTextField("",4);
    private JLabel etiqueta7 = new JLabel("Ascensor");
    private final JTextField ascensor = new JTextField("",4);
    private JLabel etiqueta8 = new JLabel("Vacacional");
    private final JTextField vacacional = new JTextField("",4);
    private ButtonGroup grupo = new ButtonGroup();
    private JButton alquilar = new JButton("Alquilar");
    private JButton volver = new JButton("Cancelar");
    private JPanel select = new JPanel(new GridLayout(1, 2));
    private GuiInmobiliaria gui;
    private JLabel texto = new JLabel("");
    private Oferta oferta;

    PanelAlquilar(GuiInmobiliaria gui){
        this.gui = gui;
        this.oferta=gui.getControlador().getOferta();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        grupo.add(alquilar);
        grupo.add(volver);
        select.add(alquilar);
        select.add(volver);
        select.setVisible(true);
        numero.setText(String.valueOf(oferta.getInmueble().getnHabitaciones()));
        numeroBan.setText(String.valueOf(oferta.getInmueble().getnBanos()));
        dim.setText(String.valueOf(oferta.getInmueble().getDimensiones()));
        planta.setText(String.valueOf(oferta.getInmueble().getPlanta()));
        direccion.setText(oferta.getInmueble().getDireccion());
        precio.setText(String.valueOf(oferta.getPrecio()));
        ascensor.setText(String.valueOf(oferta.getInmueble().getAscensor()));
        vacacional.setText(String.valueOf(oferta.isVacacional()));


        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, numero, 5, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, numero, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, etiqueta2, 0, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, numeroBan, 0, SpringLayout.WEST, numero);
        layout.putConstraint(SpringLayout.NORTH, numeroBan, 5, SpringLayout.SOUTH, numero);
        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 8, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, dim, 0, SpringLayout.WEST, numeroBan);
        layout.putConstraint(SpringLayout.NORTH, dim, 5, SpringLayout.SOUTH, numeroBan);
        layout.putConstraint(SpringLayout.EAST, etiqueta4, 0, SpringLayout.EAST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, etiqueta4, 8, SpringLayout.SOUTH, etiqueta3);
        layout.putConstraint(SpringLayout.WEST, planta, 0, SpringLayout.WEST, dim);
        layout.putConstraint(SpringLayout.NORTH, planta, 5, SpringLayout.SOUTH, dim);
        layout.putConstraint(SpringLayout.EAST, etiqueta5, 0, SpringLayout.EAST, etiqueta4);
        layout.putConstraint(SpringLayout.NORTH, etiqueta5, 8, SpringLayout.SOUTH, etiqueta4);
        layout.putConstraint(SpringLayout.WEST, direccion, 0, SpringLayout.WEST, planta);
        layout.putConstraint(SpringLayout.NORTH, direccion, 5, SpringLayout.SOUTH, planta);
        layout.putConstraint(SpringLayout.EAST, etiqueta6, 0, SpringLayout.EAST, etiqueta5);
        layout.putConstraint(SpringLayout.NORTH, etiqueta6, 8, SpringLayout.SOUTH, etiqueta5);
        layout.putConstraint(SpringLayout.WEST, precio, 0, SpringLayout.WEST, direccion);
        layout.putConstraint(SpringLayout.NORTH, precio, 5, SpringLayout.SOUTH, direccion);
        layout.putConstraint(SpringLayout.EAST, etiqueta7, 0, SpringLayout.EAST, etiqueta6);
        layout.putConstraint(SpringLayout.NORTH, etiqueta7, 8, SpringLayout.SOUTH, etiqueta6);
        layout.putConstraint(SpringLayout.WEST, etiqueta8, 0, SpringLayout.WEST, etiqueta7);
        layout.putConstraint(SpringLayout.NORTH, etiqueta8, 8, SpringLayout.SOUTH, etiqueta7);
        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, etiqueta8);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta8);
        this.add(etiqueta1);
        this.add(numero);
        this.add(numeroBan);
        this.add(etiqueta2);
        this.add(etiqueta3);
        this.add(dim);
        this.add(etiqueta4);
        this.add(etiqueta5);
        this.add(direccion);
        this.add(planta);
        this.add(etiqueta7);
        this.add(select);
        this.add(etiqueta8);
        this.add(etiqueta6);
        this.add(precio);
        this.add(vacacional);
        this.add(ascensor);
        this.setVisible(true);
        alquilar.addActionListener(this);
        volver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento){
        if(evento.getSource()==alquilar){
            gui.getControlador().alquilar(oferta);
        } else if(evento.getSource()==volver){
            gui.getControlador().volverRAvanzada();
        }

    }

    public void creadaOK(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
