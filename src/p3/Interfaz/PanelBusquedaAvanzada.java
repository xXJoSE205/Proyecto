package p3.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelBusquedaAvanzada extends JPanel {
    PanelBusquedaAvanzada() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel etiqueta1 = new JLabel("Numero de habitaciones:");
        final JTextField numero = new JTextField("", 2);
        JLabel etiqueta2 = new JLabel("Numero de banos:");
        final JTextField numeroBan = new JTextField("", 2);
        JLabel etiqueta3 = new JLabel("Dimensiones:");
        final JTextField dim = new JTextField("", 2);
        JLabel etiqueta4 = new JLabel("Numero de habitaciones:");
        final JTextField planta = new JTextField("", 2);
        JLabel etiqueta5 = new JLabel("Direccion:");
        final JTextField direccion = new JTextField("", 50);
        JCheckBox casilla1 = new JCheckBox("Ascensor");
        JButton boton = new JButton("Buscar");

        JLabel etiqueta6 = new JLabel("Precio:");
        final JTextField precio = new JTextField("", 2);
        JCheckBox casilla2 = new JCheckBox("Vacacional");

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
        layout.putConstraint(SpringLayout.EAST, casilla1, 0, SpringLayout.EAST, etiqueta6);
        layout.putConstraint(SpringLayout.NORTH, casilla1, 8, SpringLayout.SOUTH, etiqueta6);
        layout.putConstraint(SpringLayout.EAST, casilla2, 0, SpringLayout.EAST, casilla1);
        layout.putConstraint(SpringLayout.NORTH, casilla2, 8, SpringLayout.SOUTH, casilla1);
        layout.putConstraint(SpringLayout.NORTH, boton, 10, SpringLayout.SOUTH, casilla2);
        layout.putConstraint(SpringLayout.WEST, boton, 0, SpringLayout.WEST, casilla2);


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
        this.add(casilla1);
        this.setPreferredSize(new Dimension(200, 100));
        this.add(boton);
        this.add(casilla2);
        this.add(etiqueta6);
        this.add(precio);
    }
}